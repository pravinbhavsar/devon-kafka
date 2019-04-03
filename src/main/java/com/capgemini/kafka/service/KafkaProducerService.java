package com.capgemini.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.kafka.message.Message;
import com.capgemini.kafka.producer.KafkaRecordMetaData;

/**
 * @author pravbhav
 *
 */

@RestController

public interface KafkaProducerService {

  public static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

  /**
   *
   * @param topic
   * @param msg
   * @return
   */

  @RequestMapping(value = "/kafka/{topic}/message", produces = { "application/json" }, consumes = {
  "application/json" }, method = RequestMethod.POST)
  public KafkaRecordMetaData sendMessage(@PathVariable String topic, @RequestBody Message msg);

  /**
   * @param topic
   * @param key
   * @param msg
   * @return
   */

  @RequestMapping(value = "/kafka/{topic}/key/{key}", produces = { "application/json" }, consumes = {
  "application/json" }, method = RequestMethod.POST)
  KafkaRecordMetaData sendMessage(String topic, String key, Message msg);

  /**
   * @param msg
   * @return
   */

  @RequestMapping(value = "/kafka/message", produces = { "application/json" }, consumes = {
  "application/json" }, method = RequestMethod.POST)
  KafkaRecordMetaData sendMessage(Message msg);

}
