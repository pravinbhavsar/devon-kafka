package com.capgemini.kafka.service.impl;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.kafka.common.KafkaException;
import com.capgemini.kafka.common.Kafkautil;
import com.capgemini.kafka.logic.KafkaProducerLogic;
import com.capgemini.kafka.message.KafkaMessage;
import com.capgemini.kafka.message.Message;
import com.capgemini.kafka.producer.KafkaRecordMetaData;
import com.capgemini.kafka.service.KafkaProducerService;

/**
 * @author pravbhav
 *
 */

@RestController

public class KafkaProducerServiceImpl implements KafkaProducerService {

  @Override
  @RequestMapping(value = "/kafka/{topic}/message", produces = { "application/json" }, consumes = {
  "application/json" }, method = RequestMethod.POST)
  public KafkaRecordMetaData sendMessage(@PathVariable("topic") String topic, @RequestBody Message msg) {

    logger.debug("Inside KafkaProducerServiceImpl sendMessage 1");
    if (Kafkautil.isNullOrEmpty(topic)) {
      return null; // Response Entity bad Request
    }
    if (msg == null) {
      return null; // Response Entity bad Request
    }
    KafkaProducerLogic logic = new KafkaProducerLogic();
    return logic.sendMessage(topic, msg.getValue());
  }

  @Override
  @RequestMapping(value = "/kafka/{topic}/key/{key}", produces = { "application/json" }, consumes = {
  "application/json" }, method = RequestMethod.POST)
  public KafkaRecordMetaData sendMessage(@PathVariable("topic") String topic, @PathVariable("key") String key,
      @RequestBody Message msg) throws KafkaException {

    logger.debug("Inside KafkaProducerServiceImpl sendMessage 2");
    if (Kafkautil.isNullOrEmpty(topic)) {
      logger.error("Bad Request : topic is empty or null");
      throw new KafkaException("Bad Request: Topic is null or empty");

    }
    logger.debug("topic" + topic);
    logger.debug("key" + key);
    logger.debug("Message" + msg);

    KafkaProducerLogic logic = new KafkaProducerLogic();
    return logic.sendMessage(topic, key, msg.getValue());
  }

  @Override
  @RequestMapping(value = "/kafka/message", produces = { "application/json" }, consumes = {
  "application/json" }, method = RequestMethod.POST)
  public KafkaRecordMetaData sendMessage(@RequestBody Message msg) throws KafkaException {

    logger.debug("Inside KafkaProducerServiceImpl sendMessage 3");
    String topic = msg.getTopic();
    if (Kafkautil.isNullOrEmpty(topic)) {
      logger.error("Bad Request : topic is empty or null");
      throw new KafkaException("Bad Request: Topic is null or empty");

    }
    String key = msg.getKey();
    Integer partition = new Integer(msg.getPartition());
    String value = msg.getValue();
    Long timestamp = new Long(msg.getTimestamp());
    KafkaProducerLogic logic = new KafkaProducerLogic();
    if ((partition >= 0) && (timestamp < 0)) {
      return logic.sendMessage(topic, partition, key, value);
    }
    return logic.sendMessage(topic, partition, timestamp, key, value);
  }

  public KafkaRecordMetaData sendMessage(String topic, int partition, String key, String msg) throws KafkaException {

    logger.debug("Inside KafkaProducerServiceImpl sendMessage 3");
    if (Kafkautil.isNullOrEmpty(topic)) {
      logger.error(" Bad Request : topic is empty or null");
      throw new KafkaException("Bad Request: Topic is null or empty");
      // send Response Entity bad request here
    }
    Integer partitionInt = new Integer(partition);
    KafkaProducerLogic logic = new KafkaProducerLogic();
    return logic.sendMessage(topic, partition, key, msg);

  }

  public KafkaRecordMetaData sendMessage(String topic, Integer partition, Long timestamp, String key, String msg)
      throws KafkaException {

    logger.debug("Inside KafkaProducerServiceImpl sendMessage 4");
    if (Kafkautil.isNullOrEmpty(topic)) {
      logger.error(" Bad Request : topic is empty or null");
      throw new KafkaException("Bad Request: Topic is null or empty");
      // send Response Entity bad request here
    }

    KafkaProducerLogic logic = new KafkaProducerLogic();
    return logic.sendMessage(topic, partition, timestamp, key, msg);

  }

  public KafkaRecordMetaData sendMessage(KafkaMessage msg) throws KafkaException {

    logger.debug("Inside KafkaProducerServiceImpl sendMessage 5");
    String topic = msg.getTopic();
    if (Kafkautil.isNullOrEmpty(topic)) {
      logger.error(" Bad Request : topic is empty or null");
      throw new KafkaException("Bad Request: Topic is null or empty");
      // send Response Entity bad request here
    }
    KafkaProducerLogic logic = new KafkaProducerLogic();
    return logic.sendMessage(msg);
  }

  public void sendAyncMessage(KafkaMessage msg) throws KafkaException {

    logger.debug("Inside KafkaProducerServiceImpl sendMessage 6");
    String topic = msg.getTopic();
    if (Kafkautil.isNullOrEmpty(topic)) {
      logger.error(" Bad Request : topic is empty or null");
      throw new KafkaException("Bad Request: Topic is null or empty");
    }

    KafkaProducerLogic logic = new KafkaProducerLogic();
    logic.sendAsyncMessage(msg);

  }

}