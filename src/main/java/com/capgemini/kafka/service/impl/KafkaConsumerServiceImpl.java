package com.capgemini.kafka.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.kafka.logic.KafkaConsumerLogic;
import com.capgemini.kafka.message.KafkaMessage;

/**
 * @author pravbhav
 *
 */

@RestController

public class KafkaConsumerServiceImpl {

  private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);

  @RequestMapping(value = "/consume/{topic}", produces = { "application/json" }, consumes = {
  "application/json" }, method = RequestMethod.POST)
  public List<KafkaMessage> consume(@RequestParam String topic) {

    logger.debug("Inside Consume-String Method");
    KafkaConsumerLogic logic = new KafkaConsumerLogic();
    List<KafkaMessage> msgList = logic.consumeTextMessage(topic);
    return msgList;
  }

  @RequestMapping(value = "/consumeobject/{topic}/", produces = { "application/json" }, consumes = {
  "application/json" }, method = RequestMethod.POST)
  public List<KafkaMessage> sendMessage(@RequestParam String topic) {

    logger.debug("Inside Consume-Object Method");
    KafkaConsumerLogic logic = new KafkaConsumerLogic();
    List<KafkaMessage> msgList = logic.consumeObjectMessage(topic);
    return msgList;
  }

}
