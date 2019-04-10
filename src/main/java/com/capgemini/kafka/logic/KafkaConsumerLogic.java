package com.capgemini.kafka.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capgemini.kafka.common.KafkaException;
import com.capgemini.kafka.consumer.KafkaMessageConsumer;
import com.capgemini.kafka.message.KafkaMessage;

/**
 * Logic class consumes messages sent to topic Consumes message received in string and object format.
 *
 * @author pravbhav
 *
 */

public class KafkaConsumerLogic {

  private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerLogic.class);

  /**
   *
   * @param topic
   * @return
   * @throws KafkaException
   */

  public List<KafkaMessage> consumeTextMessage(String topic) throws KafkaException {

    logger.debug("Inside KafkaConsumerLogic consumeTextMessage string topic");

    KafkaMessageConsumer msgConsumer = new KafkaMessageConsumer();

    List<ConsumerRecord<byte[], byte[]>> list = msgConsumer.consumeTextMesage(topic);
    if ((list == null) || (list.isEmpty())) {
      logger.debug("No Records found returning empty list");
      return new ArrayList<KafkaMessage>();
    }
    List<KafkaMessage> custrec = new ArrayList();
    for (int i = 0; i < list.size(); i++) {
      ConsumerRecord<byte[], byte[]> rec = list.get(i);
      KafkaMessage message = new KafkaMessage();
      if (rec != null) {
        byte[] key = rec.key();
        message.setKey(new String(key));
        message.setPayload(new String(rec.value()));
        message.setTopic(rec.topic());
        message.setPartition(Integer.toString(rec.partition()));
        message.setTimestamp(Long.toString(rec.timestamp()));
        message.setTimestamp(Long.toString(rec.offset()));
        custrec.add(message);
      }

    }

    return custrec;
  }

  /**
   *
   * @param topic
   * @return
   * @throws KafkaException
   */
  public List<KafkaMessage> consumeObjectMessage(String topic) throws KafkaException {

    logger.debug("Inside KafkaConsumerLogic consumeObjectMessage string topic");

    KafkaMessageConsumer msgConsumer = new KafkaMessageConsumer();
    List<ConsumerRecord<byte[], byte[]>> list = msgConsumer.consumeObjectMessage(topic);
    if ((list == null) || (list.isEmpty())) {

      throw new KafkaException("Topic is null or empty");
    }
    List<KafkaMessage> custrec = new ArrayList();
    for (int i = 0; i < list.size(); i++) {
      ConsumerRecord<byte[], byte[]> rec = list.get(i);
      KafkaMessage message = new KafkaMessage();
      if (rec != null) {
        byte[] key = rec.key();
        message.setKey(new String(key));
        message.setPayload(new String(rec.value()));
        message.setTopic(rec.topic());
        message.setPartition(Integer.toString(rec.partition()));
        message.setTimestamp(Long.toString(rec.timestamp()));
        message.setTimestamp(Long.toString(rec.offset()));
        custrec.add(message);
      }
    }
    return custrec;
  }

}
