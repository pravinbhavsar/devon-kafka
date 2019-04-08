package com.capgemini.kafka.producer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.capgemini.kafka.config.KafkaProducerProperties;
import com.capgemini.kafka.message.KafkaMessage;

/**
 * @author pravbhav
 *
 */

@Component
public class KafkaMessageProducer {

  private static final Logger logger = LoggerFactory.getLogger(KafkaMessageProducer.class);

  KafkaProducerProperties prodProperties = new KafkaProducerProperties();

  KafkaProducer<String, String> strProducer;

  KafkaProducer<String, KafkaMessage> objProducer;

  /*
   * create producer object to send String message
   *
   *
   */
  public Producer<String, String> createProducer() {

    if (this.strProducer != null) {
      return this.strProducer;

    } else {
      Properties p = this.prodProperties.getProperties();
      this.strProducer = new KafkaProducer<String, String>(p);
      return this.strProducer;
    }
  }

  /*
   * create producer object to send Object message ( KafkaMessage )
   *
   */

  public KafkaProducer<String, KafkaMessage> createObjProducer() {

    logger.debug("Inside KafkaProducer  createObjProducer");

    if (this.objProducer != null) {
      return this.objProducer;

    } else {
      Properties p = this.prodProperties.getProperties();
      p.setProperty("key.serializer", "com.capgemini.kafka.config.KafkaMessageSerializer");
      p.setProperty("value.serializer", "com.capgemini.kafka.config.KafkaMessageSerializer");
      this.objProducer = new KafkaProducer<String, KafkaMessage>(p);
      return this.objProducer;
    }
  }

  /*
   * send a text message to producer
   *
   * @arg1 - message string, this is synchronous message Message is sent without key and patition
   */
  public RecordMetadata sendTextMessage(String topic, String message) {

    logger.debug("Inside KafkaProducer  sendTextMessage");

    final Producer<String, String> producer = createProducer();
    RecordMetadata metadata = null;
    try {

      metadata = producer.send(new ProducerRecord<String, String>(topic, message)).get();

    } catch (Exception ex) {
      logger.error("Exception ex" + ex.getMessage());

    } finally {
      producer.flush();
      producer.close();
    }

    return metadata;
  }

  /*
   * send a text message to producer
   *
   * @arg1 - message string, this is synchronous message Message is sent with key and
   *
   */

  public RecordMetadata sendTextMessage(String topic, String key, String message) {

    logger.debug("Inside KafkaProducer  sendTextMessage2");

    final Producer<String, String> producer = createProducer();
    RecordMetadata metadata = null;
    try {

      metadata = producer.send(new ProducerRecord<String, String>(topic, key, message)).get();

    } catch (Exception ex) {
      logger.error("Exception ex" + ex.getMessage());

    } finally {
      producer.flush();
      producer.close();
    }

    return metadata;
  }

  /*
   * send a text message to producer
   *
   * @arg1 - message string, this is synchronous message Message is sent with key and
   *
   */

  public RecordMetadata sendTextMessage(String topic, Integer partition, String keyStr, String message) {

    logger.debug("Inside KafkaProducer  sendTextMessage 3");

    final Producer<String, String> producer = createProducer();
    RecordMetadata metadata = null;
    try {

      metadata = producer.send(new ProducerRecord<String, String>(topic, partition, keyStr, message)).get();

    } catch (Exception ex) {
      logger.error("Exception ex" + ex.getMessage());

    } finally {
      producer.flush();
      producer.close();
    }

    return metadata;
  }

  public RecordMetadata sendTextMessage(String topic, Integer partition, Long timestamp, String key, String message) {

    logger.debug("Inside KafkaProducer  sendTextMessage 4");

    final Producer<String, String> producer = createProducer();
    RecordMetadata metadata = null;
    try {

      metadata = producer.send(new ProducerRecord<String, String>(topic, partition, timestamp, key, message)).get();

    } catch (Exception ex) {
      logger.error("Exception ex" + ex.getMessage());

    } finally {
      producer.flush();
      producer.close();
    }

    return metadata;
  }

  /*
   * send a text message to producer
   *
   * @arg1 - message string, this is synchronous message Message is sent with key and
   *
   */

  /**
   * Send KafkaMessage message to kafa
   *
   * @param String topic
   * @param KafkaMessage msg
   */

  public RecordMetadata send(KafkaMessage message) {

    logger.debug("Inside KafkaProducer  send");
    logger.debug("Kafka Messgae" + message.getMessageId() + message.getPayload());
    RecordMetadata recdata = null;
    String topic = message.getTopic();
    final Producer<String, KafkaMessage> producer = createObjProducer();
    try {

      recdata = (RecordMetadata) producer.send(new ProducerRecord(topic, message)).get();
    } catch (InterruptedException e) {
      logger.info("Error occured in InterruptedException is thrown ");
      e.printStackTrace();
    } catch (ExecutionException e) {
      logger.info("Error occured in InterruptedException is thrown ");
      e.printStackTrace();
    } finally {
      producer.flush();
      producer.close();
    }
    return recdata;

  }

  /*
   *
   * send message to kafka Asynchronously
   *
   */
  @Async
  public void sendAsync(KafkaMessage message) {

    logger.debug("Inside KafkaProducer  sendAsync");

    final Producer<String, KafkaMessage> producer = createObjProducer();
    String topic = message.getTopic();

    if (topic == null || topic.isEmpty()) {
      logger.error("sendAsync : Topic should not be null or empty");
    }

    ListenableFuture<SendResult<String, KafkaMessage>> future =
        (ListenableFuture<SendResult<String, KafkaMessage>>) producer.send(new ProducerRecord(topic, message));
    future.addCallback(new ListenableFutureCallback<SendResult<String, KafkaMessage>>() {

      @Override
      public void onSuccess(final SendResult<String, KafkaMessage> message) {

        logger.info("sent message= " + message + " with offset= " + message.getRecordMetadata().offset());
      }

      @Override
      public void onFailure(final Throwable throwable) {

        logger.error("unable to send message= " + message, throwable);
      }
    });
  }

}