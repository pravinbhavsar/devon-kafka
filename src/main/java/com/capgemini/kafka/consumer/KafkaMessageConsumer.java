package com.capgemini.kafka.consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.capgemini.kafka.config.KafkaConsumerProperties;

/**
 *
 *
 * @author pravbhav
 *
 */

@Component
public class KafkaMessageConsumer {

  private static final Logger logger = LoggerFactory.getLogger(KafkaMessageConsumer.class);

  KafkaConsumerProperties conProperties = new KafkaConsumerProperties();

  KafkaConsumer<byte[], byte[]> strConsumer;

  KafkaConsumer<byte[], byte[]> objConsumer;

  List<ConsumerRecord<byte[], byte[]>> strBuffer = new ArrayList<ConsumerRecord<byte[], byte[]>>();

  List<ConsumerRecord<byte[], byte[]>> objBuffer = new ArrayList<ConsumerRecord<byte[], byte[]>>();

  /*
   * create producer object to send String message
   *
   *
   */
  public KafkaConsumer<byte[], byte[]> createConsumer() {

    logger.debug("Inside KafkaMessageConsumer - createConsumer ");

    if (this.strConsumer != null) {
      return this.strConsumer;

    } else {
      Properties p = this.conProperties.getProperties();
      this.strConsumer = new KafkaConsumer<byte[], byte[]>(p);
      return this.strConsumer;
    }
  }

  /*
   * create producer object to send Object message ( KafkaMessage )
   *
   */

  public KafkaConsumer<byte[], byte[]> createObjConsumer() {

    logger.debug("Inside KafkaMessageConsumer - createObjConsumer ");

    if (this.objConsumer != null) {
      return this.objConsumer;

    } else {
      Properties p = this.conProperties.getProperties();
      p.setProperty("key.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
      p.setProperty("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
      this.objConsumer = new KafkaConsumer<byte[], byte[]>(p);
      return this.objConsumer;
    }
  }

  /**
   *
   * @param consumer
   * @param topicName
   */

  public void subscribeTextConsumer(Consumer<byte[], byte[]> consumer, String topicName) {

    logger.debug("Inside KafkaMessageConsumer - subscribeTextConsumer ");

    consumer.subscribe(Arrays.asList(topicName));
  }

  public void subscribeObjConsumer(Consumer<byte[], byte[]> consumer, String topicName) {

    logger.debug("Inside KafkaMessageConsumer - subscribeObjConsumer ");

    consumer.subscribe(Arrays.asList(topicName));
  }

  public void unSubscribe(Consumer consumer) {

    logger.debug("Inside KafkaMessageConsumer - unSubscribe ");
    consumer.unsubscribe();
  }

  /*
   * consume messages using poll method
   *
   */
  public List<ConsumerRecord<byte[], byte[]>> consumeTextMesage(String topicName) {

    logger.debug("Inside KafkaMessageConsumer - consumeTextMesage ");

    final Consumer<byte[], byte[]> consumer = createConsumer();
    subscribeTextConsumer(consumer, topicName);

    final int giveUp = 100;
    int noRecordsCount = 0;
    while (true) {
      final ConsumerRecords<byte[], byte[]> consumerRecords = consumer.poll(1000);
      if (consumerRecords.count() == 0) {
        noRecordsCount++;
        if (noRecordsCount > giveUp)
          break;
        else
          continue;
      }
      consumerRecords.forEach(record -> {
        logger.debug("Key" + record.key());
        logger.debug("Value" + record.value());
        logger.debug("Partition" + record.partition());
        logger.debug("offset" + record.offset());
        this.strBuffer.add(record);

      });
      consumer.commitAsync();
    }
    consumer.close();
    logger.debug("DONE");

    return this.strBuffer;
  }

  /*
   * consume messages using poll method
   *
   */

  public List<ConsumerRecord<byte[], byte[]>> consumeObjectMessage(String topicName) {

    logger.debug("Inside KafkaMessageConsumer - consumeObjectMessage ");

    final Consumer<byte[], byte[]> consumer = createObjConsumer();
    subscribeObjConsumer(consumer, topicName);

    while (true) {
      final ConsumerRecords<byte[], byte[]> consumerRecords = consumer.poll(1000);

      if (consumerRecords == null || consumerRecords.isEmpty()) {
        break;
      }

      consumerRecords.forEach(record -> {
        logger.debug("Key" + record.key());
        logger.debug("Value" + record.value());
        logger.debug("Partition" + record.partition());
        logger.debug("offset" + record.offset());

        this.objBuffer.add(record);

      });
      consumer.commitAsync();
    }
    consumer.close();
    logger.debug("DONE");
    return this.objBuffer;
  }

}
