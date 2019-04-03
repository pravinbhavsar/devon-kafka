package com.capgemini.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.kafka.message.KafkaMessage;

/**
 * @author pravbhav
 *
 */

@RestController

public interface KafkaConsumerService {

  public static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

  @RequestMapping(value = "/kafka/send/{topic}", produces = { "application/json" }, consumes = {
  "application/json" }, method = RequestMethod.POST)
  public void sendMessage(@RequestBody String msg, @RequestParam String topic);

  @RequestMapping(value = "kafka/sendobject/{topic}", produces = { "application/json" }, consumes = {
  "application/json" }, method = RequestMethod.POST)
  public void sendMessage(@RequestBody String msg, @RequestParam String topic, @RequestParam String id);

  @RequestMapping(value = "/kafka/{topic}/{id}/{partition}", produces = { "application/json" }, consumes = {
  "application/json" }, method = RequestMethod.POST)
  public void sendMessage(@RequestBody String msg, @RequestParam String topic, @RequestParam String id,
      @RequestParam String partitionId);

  @RequestMapping(value = "/kafka/message", produces = { "application/json" }, consumes = {
  "application/json" }, method = RequestMethod.POST)
  public void sendMessage(@RequestBody KafkaMessage msg);

  @RequestMapping(value = "/kafka/asyncmsg", produces = { "application/json" }, consumes = {
  "application/json" }, method = RequestMethod.POST)
  public void sendAsyncMessage(@RequestBody KafkaMessage msg);

}
