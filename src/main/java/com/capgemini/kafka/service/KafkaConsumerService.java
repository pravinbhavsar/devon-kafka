package com.capgemini.kafka.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.kafka.common.KafkaException;
import com.capgemini.kafka.message.KafkaMessage;

/**
 * @author pravbhav
 *
 */

@RestController

public interface KafkaConsumerService {

  public static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

  /**
   *
   * @param topic
   * @return
   * @throws KafkaException
   */
  @RequestMapping(value = "/kafka/consume/{topic}", produces = { "application/json" }, consumes = {
  "application/json" }, method = RequestMethod.POST)
  public List<KafkaMessage> consume(@PathVariable String topic) throws KafkaException;

  /**
   *
   * @param topic
   * @return
   * @throws KafkaException
   */
  @RequestMapping(value = "/kafka/consumeobject/{topic}", produces = { "application/json" }, consumes = {
  "application/json" }, method = RequestMethod.POST)
  public List<KafkaMessage> consumeObjectMessage(@PathVariable String topic) throws KafkaException;

}
