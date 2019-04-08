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

  /**
   *
   * @param topic
   * @param msg
   * @return
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

  /**
   * send message to kafka topic
   * 
   * @param topic
   * @param key
   * @param msg
   * @return
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

  /**
   * Send message to topic and partition
   * 
   * @param topic
   * @param partition
   * @param key
   * @param msg
   * @return
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

  /**
   * send message to kafka topic to specified partition, with timestamp, key and message
   *
   * @param topic
   * @param partition
   * @param timestamp
   * @param key
   * @param msg
   * @return KafkaRecordMetaData
   */

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

  /**
   * send a message as KafkaMessage Object
   *
   * @param msg
   * @return KafkaRecordMetaData
   */
  public KafkaRecordMetaData sendMessage(KafkaMessage msg) {

    logger.debug("Kafka SendMessage Called" + msg);
    KafkaMessageProducer producer = new KafkaMessageProducer();
    RecordMetadata metadata = producer.send(msg);
    KafkaConverter converter = new KafkaConverter();
    KafkaRecordMetaData kmetadata = converter.convertKafkaMetaData(metadata);
    return kmetadata;

  }

  /**
   * send a message as Message Object in Async manner
   *
   * @param msg
   */
  public void sendAsyncMessage(KafkaMessage msg) {

    logger.debug("Kafka SendMessage Called" + msg);
    KafkaMessageProducer producer = new KafkaMessageProducer();
    producer.sendAsync(msg);

  }

}
