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
import com.capgemini.kafka.message.KafkaMessage;

/**
 * @author pravbhav
 *
 */

@Component
public class KafkaMessageConsumer {

  private static final Logger logger = LoggerFactory.getLogger(KafkaMessageConsumer.class);

  KafkaConsumerProperties conProperties = new KafkaConsumerProperties();

  KafkaConsumer<String, String> strConsumer;

  KafkaConsumer<String, KafkaMessage> objConsumer;

  List<ConsumerRecord<String, String>> strBuffer = new ArrayList<ConsumerRecord<String, String>>();

  List<ConsumerRecord<String, KafkaMessage>> objBuffer = new ArrayList<ConsumerRecord<String, KafkaMessage>>();

  /*
   * create producer object to send String message
   *
   *
   */
  public KafkaConsumer<String, String> createConsumer() {

    if (this.strConsumer != null) {
      return this.strConsumer;

    } else {
      Properties p = this.conProperties.getProperties();
      this.strConsumer = new KafkaConsumer<String, String>(p);
      return this.strConsumer;
    }
  }

  /*
   * create producer object to send Object message ( KafkaMessage )
   *
   */

  public KafkaConsumer<String, KafkaMessage> createObjConsumer() {

    if (this.objConsumer != null) {
      return this.objConsumer;

    } else {
      Properties p = this.conProperties.getProperties();
      p.setProperty("key.deserializer", "com.capgemini.kafka.config.KafkaMessageDeSerializer");
      p.setProperty("value.deserializer", "com.capgemini.kafka.config.KafkaMessageDeSerializer");
      this.objConsumer = new KafkaConsumer<String, KafkaMessage>(p);
      return this.objConsumer;
    }
  }

  /**
   *
   * @param consumer
   * @param topicName
   */

  public void subscribeTextConsumer(Consumer<String, String> consumer, String topicName) {

    consumer.subscribe(Arrays.asList(topicName));
  }

  public void subscribeObjConsumer(Consumer<String, KafkaMessage> consumer, String topicName) {

    consumer.subscribe(Arrays.asList(topicName));
  }

  public void unSubscribe(Consumer consumer) {

    consumer.unsubscribe();
  }

  /*
   * consume messages using poll method
   *
   */
  public List<ConsumerRecord<String, String>> consumeTextMesage(String topicName) {

    final Consumer<String, String> consumer = createConsumer();
    subscribeTextConsumer(consumer, topicName);

    final int giveUp = 100;
    int noRecordsCount = 0;
    while (true) {
      final ConsumerRecords<String, String> consumerRecords = consumer.poll(1000);
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

  public List<ConsumerRecord<String, KafkaMessage>> consumeObjectMessage(String topicName) {

    final Consumer<String, KafkaMessage> consumer = createObjConsumer();
    subscribeObjConsumer(consumer, topicName);

    final int giveUp = 100;
    int noRecordsCount = 0;

    while (true) {
      final ConsumerRecords<String, KafkaMessage> consumerRecords = consumer.poll(1000);
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
        this.objBuffer.add(record);

      });
      consumer.commitAsync();
    }
    consumer.close();
    logger.debug("DONE");
    return this.objBuffer;
  }

}
