package com.capgemini.kafka.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capgemini.kafka.consumer.KafkaMessageConsumer;
import com.capgemini.kafka.message.KafkaMessage;

/**
 * @author pravbhav
 *
 */
public class KafkaConsumerLogic {

  private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerLogic.class);

  public List<KafkaMessage> consumeTextMessage(String topic) {

    KafkaMessageConsumer msgConsumer = new KafkaMessageConsumer();
    List<ConsumerRecord<String, String>> list = msgConsumer.consumeTextMesage(topic);
    if ((list == null) || (list.isEmpty())) {

      return null;

    }
    List<KafkaMessage> custrec = new ArrayList();
    for (int i = 0; i < list.size(); i++) {
      ConsumerRecord<String, String> rec = list.get(i);
      KafkaMessage message = new KafkaMessage();
      message.setOffset(rec.offset());
      message.setPartition(rec.partition());
      message.setTimestamp(rec.timestamp());
      message.setPayload(rec.value());
      message.setKey(rec.key());
      custrec.add(message);
    }
    return custrec;
  }

  public List<KafkaMessage> consumeObjectMessage(String topic) {

    KafkaMessageConsumer msgConsumer = new KafkaMessageConsumer();
    List<ConsumerRecord<String, KafkaMessage>> list = msgConsumer.consumeObjectMessage(topic);
    if ((list == null) || (list.isEmpty())) {

      return null;

    }
    List<KafkaMessage> custrec = new ArrayList();
    for (int i = 0; i < list.size(); i++) {
      ConsumerRecord<String, KafkaMessage> rec = list.get(i);
      String key = rec.key();
      KafkaMessage message = rec.value();
      message.setKey(key);
      message.setOffset(rec.offset());
      message.setPartition(rec.partition());
      message.setTimestamp(rec.timestamp());
      message.setPayload(rec.value());
      custrec.add(message);
    }
    return custrec;
  }

}
