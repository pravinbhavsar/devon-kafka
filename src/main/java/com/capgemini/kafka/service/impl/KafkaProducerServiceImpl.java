package com.capgemini.kafka.service.impl;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.kafka.logic.KafkaProducerLogic;
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

    if ((topic == null) || topic.isEmpty()) {
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
      @RequestBody Message msg) {

    if ((topic == null) || topic.isEmpty()) {
      return null; // Response Entity bad Request
    }
    if (msg == null) {
      return null; // Response Entity bad Request
    }
    System.out.println("topic" + topic);
    System.out.println("key" + key);
    System.out.println("Message" + msg);

    KafkaProducerLogic logic = new KafkaProducerLogic();
    return logic.sendMessage(topic, key, msg.getValue());
  }

  @Override
  @RequestMapping(value = "/kafka/message", produces = { "application/json" }, consumes = {
  "application/json" }, method = RequestMethod.POST)
  public KafkaRecordMetaData sendMessage(@RequestBody Message msg) {

    if (msg == null) {
      return null; // Response Entity bad Request
    }
    String topic = msg.getTopic();
    if ((topic == null) || topic.isEmpty()) {
      return null; // Response Entity bad Request
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

}
