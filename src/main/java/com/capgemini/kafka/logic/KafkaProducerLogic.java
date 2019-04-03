package com.capgemini.kafka.logic;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capgemini.kafka.message.KafkaMessage;
import com.capgemini.kafka.producer.KafkaConverter;
import com.capgemini.kafka.producer.KafkaMessageProducer;
import com.capgemini.kafka.producer.KafkaRecordMetaData;

/**
 * Used by Service class to send messages to kafka topic Messages can be sent in sync and aysnc format
 *
 * @author pravbhav
 *
 */
public class KafkaProducerLogic {

  private static final Logger logger = LoggerFactory.getLogger(KafkaProducerLogic.class);

  /*
   * send a message as string
   */
  public KafkaRecordMetaData sendMessage(String topic, String msg) {

    logger.debug("Kafka SendMessage Called" + msg);
    if (topic.isEmpty() || topic == null) {
      return null;
      // send Response Entity bad request here
    }
    KafkaMessageProducer producer = new KafkaMessageProducer();
    RecordMetadata metadata = producer.sendTextMessage(topic, msg);
    logger.debug("Topic" + metadata.topic());
    logger.debug("Offset" + metadata.offset());
    KafkaConverter converter = new KafkaConverter();
    KafkaRecordMetaData kmetadata = converter.convertKafkaMetaData(metadata);
    logger.debug("Exiting Kafka SendMessage");

    return kmetadata;
  }

  /*
   * send a message as string
   */
  public KafkaRecordMetaData sendMessage(String topic, String key, String msg) {

    logger.debug("Kafka SendMessage Called" + msg);
    KafkaMessageProducer producer = new KafkaMessageProducer();
    RecordMetadata metadata = producer.sendTextMessage(topic, key, msg);
    logger.debug("Topic" + metadata.topic());
    logger.debug("Offset" + metadata.offset());
    KafkaConverter converter = new KafkaConverter();
    KafkaRecordMetaData kmetadata = converter.convertKafkaMetaData(metadata);
    return kmetadata;
  }

  /*
   * send a message as string
   */
  public KafkaRecordMetaData sendMessage(String topic, int partition, String key, String msg) {

    logger.debug("Kafka SendMessage Called" + msg);
    KafkaMessageProducer producer = new KafkaMessageProducer();
    RecordMetadata metadata = producer.sendTextMessage(topic, partition, key, msg);
    logger.debug("Topic" + metadata.topic());
    logger.debug("Offset" + metadata.offset());
    KafkaConverter converter = new KafkaConverter();
    KafkaRecordMetaData kmetadata = converter.convertKafkaMetaData(metadata);
    return kmetadata;

  }

  public KafkaRecordMetaData sendMessage(String topic, Integer partition, Long timestamp, String key, String msg) {

    logger.debug("Kafka SendMessage Called" + msg);
    KafkaMessageProducer producer = new KafkaMessageProducer();
    RecordMetadata metadata = producer.sendTextMessage(topic, partition, timestamp, key, msg);
    logger.debug("Topic" + metadata.topic());
    logger.debug("Offset" + metadata.offset());
    KafkaConverter converter = new KafkaConverter();
    KafkaRecordMetaData kmetadata = converter.convertKafkaMetaData(metadata);
    return kmetadata;

  }

  /*
   * send a message as Message Object
   */

  public void sendMessage(KafkaMessage msg) {

    logger.debug("Kafka SendMessage Called" + msg);
    KafkaMessageProducer producer = new KafkaMessageProducer();
    producer.send(msg);

  }

  /*
   * send a message as Message Object in Async manner
   */

  public void sendAsyncMessage(KafkaMessage msg) {

    logger.debug("Kafka SendMessage Called" + msg);
    KafkaMessageProducer producer = new KafkaMessageProducer();
    producer.sendAsync(msg);

  }

}
