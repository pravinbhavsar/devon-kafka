package com.capgemini.kafka.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.kafka.common.KafkaException;
import com.capgemini.kafka.common.Kafkautil;
import com.capgemini.kafka.logic.KafkaConsumerLogic;
import com.capgemini.kafka.message.KafkaMessage;
import com.capgemini.kafka.service.KafkaConsumerService;

/**
 * @author pravbhav
 *
 */

@RestController

public class KafkaConsumerServiceImpl implements KafkaConsumerService {

  private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);

  @RequestMapping(value = "/kafka/consume/{topic}", produces = { "application/json" }, consumes = {
  "application/json" }, method = RequestMethod.POST)
  public List<KafkaMessage> consume(@PathVariable String topic) throws KafkaException {

    logger.debug("Inside Consume-String Method");

    if (Kafkautil.isNullOrEmpty(topic)) {
      logger.error(" Bad Request : topic is empty or null");
      throw new KafkaException("Bad Request: Topic is null or empty");
    }

    KafkaConsumerLogic logic = new KafkaConsumerLogic();
    List<KafkaMessage> msgList = null;
    try {
      msgList = logic.consumeTextMessage(topic);
    } catch (KafkaException e) {

      logger.error("Exception" + e.getMessage());
      e.printStackTrace();
    }
    return msgList;
  }

  @RequestMapping(value = "/kafka/consumeobject/{topic}", produces = { "application/json" }, consumes = {
  "application/json" }, method = RequestMethod.POST)
  public List<KafkaMessage> consumeObjectMessage(@PathVariable String topic) throws KafkaException {

    logger.debug("Inside Consume-Object Method");

    if (Kafkautil.isNullOrEmpty(topic)) {
      logger.error(" Bad Request : topic is empty or null");
      throw new KafkaException("Bad Request: Topic is null or empty");
    }

    KafkaConsumerLogic logic = new KafkaConsumerLogic();
    List<KafkaMessage> msgList = null;
    try {
      msgList = logic.consumeObjectMessage(topic);
    } catch (KafkaException e) {
      logger.error("Exception" + e.getMessage());
      e.printStackTrace();
    }
    return msgList;
  }

}
