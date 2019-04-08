package com.capgemini.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
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

import com.capgemini.kafka.config.KafkaProducerProperties;
import com.capgemini.kafka.logic.KafkaProducerLogic;
import com.capgemini.kafka.producer.KafkaMessageProducer;
import com.capgemini.kafka.producer.KafkaRecordMetaData;

import mockit.MockUp;
import mockit.integration.junit4.JMockit;

/**
 * @author dichowdh
 *
 */
@RunWith(JMockit.class)
public class KafkaProducerLogicTest {

  @Rule
  public MockitoRule rule = MockitoJUnit.rule();

  private RecordMetadata recordMetadata;

  @Mock
  KafkaMessageProducer producer;

  @Mock
  Environment environment;

  @Mock
  static KafkaProducerProperties kafkaProducerProperties;

  @Mock
  Properties properties;

  @Mock
  KafkaProducer<String, String> strProducer;

  @InjectMocks
  KafkaProducerLogic kafkaProducerLogic;

  /**
   *
   */
  public void mockKafkaProducer() {

    new MockUp<KafkaProducer>() {
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

    kafkaProducerProperties = new KafkaProducerProperties();
    kafkaProducerProperties.setEnvironment(this.environment);

  }

  /**
   *
   */
  @Test
  public void sendMessageTest() {

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
    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.producer.key.serializer"))
        .thenReturn("com.capgemini.kafka.config.KafkaMessageSerializer");
    // kafka.producer.value.serializer
    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.producer.value.serializer"))
        .thenReturn("com.capgemini.kafka.config.KafkaMessageSerializer");
    Mockito.when(this.producer.sendTextMessage(Mockito.anyString(), Mockito.anyString()))
        .thenReturn(this.recordMetadata);
    KafkaRecordMetaData result = this.kafkaProducerLogic.sendMessage("test", value);

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
    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.producer.key.serializer"))
        .thenReturn("com.capgemini.kafka.config.KafkaMessageSerializer");
    // kafka.producer.value.serializer
    Mockito.when(KafkaProducerProperties.getEnvironment().getProperty("kafka.producer.value.serializer"))
        .thenReturn("com.capgemini.kafka.config.KafkaMessageSerializer");
    Mockito.when(this.producer.sendTextMessage(Mockito.anyString(), Mockito.anyString()))
        .thenReturn(this.recordMetadata);

    KafkaRecordMetaData result = this.kafkaProducerLogic.sendMessage("test", value, "msg");
  }

}
