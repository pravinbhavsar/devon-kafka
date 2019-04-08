package com.capgemini.kafka;

import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
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

import com.capgemini.kafka.common.KafkaException;
import com.capgemini.kafka.config.KafkaConsumerProperties;
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

  private List<ConsumerRecord<String, String>> recordMetadata;

  @Mock
  KafkaMessageConsumer consumer;

  @Mock
  Environment environment;

  @Mock
  static KafkaConsumerProperties kafkaConsumerProperties;

  @Mock
  Properties properties;

  @Mock
  KafkaConsumer<String, String> strConsumer;

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

    Mockito.when(KafkaConsumerProperties.getEnvironment().getProperty(Mockito.anyString())).thenReturn(value);
    Mockito.when(KafkaConsumerProperties.getEnvironment().getProperty("kafka.consumer.client.id"))
        .thenReturn("console-consumer");
    // kafka.producer.retries

    Mockito.when(KafkaConsumerProperties.getEnvironment().getProperty("kafka.consumer.bootstrap.servers"))
        .thenReturn("10.76.3.49:9092");

    // kafka.producer.bootstrap.servers
    Mockito.when(KafkaConsumerProperties.getEnvironment().getProperty("kafka.consumer.enable.auto.commit"))
        .thenReturn("true");

    // kafka.producer.acks
    /*
     * Mockito.when(KafkaConsumerProperties.getEnvironment().getProperty("kafka.consumer.auto.commit.interval"))
     * .thenReturn("l100");
     */
    // kafka.producer.batch.size
    Mockito.when(KafkaConsumerProperties.getEnvironment().getProperty("kafka.consumer.buffer.memory"))
        .thenReturn("33554432");
    // kafka.producer.linger.ms
    Mockito.when(KafkaConsumerProperties.getEnvironment().getProperty("kafka.consumer.session.timeout.ms"))
        .thenReturn("30000");

    Mockito.when(KafkaConsumerProperties.getEnvironment().getProperty("kafka.consumer.group.id")).thenReturn("test");

    // kafka.producer.key.serializer
    Mockito.when(KafkaConsumerProperties.getEnvironment().getProperty("kafka.consumer.key.deserializer"))
        .thenReturn("com.capgemini.kafka.config.KafkaMessageDeSerializer");
    // kafka.producer.value.serializer
    Mockito.when(KafkaConsumerProperties.getEnvironment().getProperty("kafka.consumer.value.deserializer"))
        .thenReturn("com.capgemini.kafka.config.KafkaMessageDeSerializer");
    Mockito.when(this.consumer.consumeTextMesage(Mockito.anyString())).thenReturn(this.recordMetadata);
    try {
      List<KafkaMessage> result = this.kafkaConsumerLogic.consumeTextMessage("test");

    } catch (KafkaException e) {

      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
