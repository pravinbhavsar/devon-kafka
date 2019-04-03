package com.capgemini.kafka;

import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.core.env.Environment;

import com.capgemini.kafka.config.KafkaConsumerProperties;
import com.capgemini.kafka.config.KafkaProducerProperties;
import com.capgemini.kafka.consumer.KafkaMessageConsumer;
import com.capgemini.kafka.logic.KafkaConsumerLogic;
import com.capgemini.kafka.message.KafkaMessage;

import mockit.MockUp;
import mockit.integration.junit4.JMockit;

/**
 * @author dichowdh
 *
 */
@RunWith(JMockit.class)
public class KafkaConsumerLogicTest {

  @Rule
  public MockitoRule rule = MockitoJUnit.rule();

  private RecordMetadata recordMetadata;

  @Mock
  KafkaMessageConsumer consumer;

  @Mock
  Environment environment;

  @Mock
  static KafkaConsumerProperties kafkaConsumerProperties;

  @Mock
  Properties properties;

  @Mock
  KafkaConsumer<String, String> strProducer;

  @InjectMocks
  KafkaConsumerLogic kafkaConsumerLogic;

  /**
   *
   */
  public void mockKafkaConsumer() {

    new MockUp<KafkaConsumer>() {
      @mockit.Mock
      public void $init() {

      }
    };
  }

  /**
   *
   */
  @Before
  public void setUp() {

    MockitoAnnotations.initMocks(this);

    kafkaConsumerProperties = new KafkaConsumerProperties();
    kafkaConsumerProperties.setEnvironment(this.environment);

  }

  /**
   *
   */

  @Test
  public void sendMessageTest() {

    String value = "ABC";

    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty(Mockito.anyString())).thenReturn(value);
    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.consumer.client.id"))
        .thenReturn("console-consumer");
    // kafka.producer.retries
    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.consumer.topic")).thenReturn("test");
    // kafka.producer.bootstrap.servers
    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.consumer.bootstrap.servers"))
        .thenReturn("10.76.3.49:9092");
    // kafka.producer.acks
    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.consumer.enable.auto.commit"))
        .thenReturn("true");
    // kafka.producer.batch.size
    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.consumer.auto.commit.interval"))
        .thenReturn("1000");
    // kafka.producer.linger.ms
    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.consumer.session.timeout.ms"))
        .thenReturn("30000");

    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.consumer.group.id")).thenReturn("test");
    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.consumer.enable.auto.commit"))
        .thenReturn("true");
    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.consumer.auto.commit.interval"))
        .thenReturn("5000");
    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.consumer.buffer.memory"))
        .thenReturn("33554432");

    // kafka.consumer.key.deserializer
    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.consumer.key.deserializer"))
        .thenReturn("org.apache.kafka.common.serialization.KafkaMessageDeSerializer");
    // kafka.consumer.value.deserializer
    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.consumer.value.deserializer"))
        .thenReturn("com.capgemini.kafka.config.KafkaMessageDeSerializer");
     Mockito.when(this.consumer.consumeTextMesage(Mockito.anyString()))
     List<KafkaMessage> result = this.kafkaConsumerLogic.consumeTextMessage("test");

  }

  /**
   *
   */
  @Test
  public void sendMessageTest1() {

    String value = "ABC";

    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty(Mockito.anyString())).thenReturn(value);
    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.producer.buffer.memory"))
        .thenReturn("123");
    // kafka.producer.retries
    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.producer.retries")).thenReturn("10");
    // kafka.producer.bootstrap.servers
    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.producer.bootstrap.servers"))
        .thenReturn("10.76.3.49:9092");
    // kafka.producer.acks
    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.producer.acks")).thenReturn("all");
    // kafka.producer.batch.size
    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.producer.batch.size")).thenReturn("1");
    // kafka.producer.linger.ms
    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.producer.linger.ms")).thenReturn("1");
    // kafka.producer.key.serializer
    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.consumer.key.deserializer"))
        .thenReturn("com.capgemini.kafka.config.KafkaMessageDeSerializer");

    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.consumer.value.deserializer"))
        .thenReturn("com.capgemini.kafka.config.KafkaMessageDeSerializer");
    Mockito.when(this.consumer.consumeTextMesage(Mockito.anyString()));
    // .thenReturn(this.recordMetadata);

    List<KafkaMessage> result = this.kafkaConsumerLogic.consumeTextMessage("test");
  }

}
