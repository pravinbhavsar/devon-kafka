package com.capgemini.kafka.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
public class KafkaProducerProperties implements EnvironmentAware {

  static Environment environment;

  @Override
  public void setEnvironment(Environment environment) {

    KafkaProducerProperties.environment = environment;
  }

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {

    return new PropertySourcesPlaceholderConfigurer();
  }

  /**
   * @return environment
   */
  public static Environment getEnvironment() {

    return environment;
  }

  /**
   * @return clientId
   */
  public String getClientId() {

    System.out.println(environment.getProperty("kafka.producer.client.id"));
    return environment.getProperty("kafka.producer.client.id");
  }

  /**
   * @return bootstrapservers
   */
  public String getBootstrapservers() {

    System.out.println(environment.getProperty("kafka.producer.bootstrap.servers"));
    // kafka.producer.bootstrap.servers
    return environment.getProperty("kafka.producer.bootstrap.servers");

  }

  /**
   * @return acks
   */
  public String getAcks() {

    return environment.getProperty("kafka.producer.acks").trim();
  }

  /**
   * @return retries
   */
  public String getRetries() {

    return environment.getProperty("kafka.producer.retries");

  }

  /**
   * @return batchsize
   */
  public String getBatchsize() {

    return environment.getProperty("kafka.producer.batch.size");

  }

  /**
   * @return lingerms
   */
  public String getLingerms() {

    return environment.getProperty("kafka.producer.linger.ms");

  }

  /**
   * @return buffermemory
   */
  public String getBuffermemory() {

    return environment.getProperty("kafka.producer.buffer.memory");

  }

  /**
   * @return keyserializer
   */
  public String getKeyserializer() {

    return environment.getProperty("kafka.producer.key.serializer");

  }

  /**
   * @return valueserializer
   */
  public String getValueserializer() {

    return environment.getProperty("kafka.producer.value.serializer");

  }

  public Properties getProperties() {

    Properties prop = new Properties();
    prop.setProperty("client.id", getClientId());
    prop.setProperty("bootstrap.servers", getBootstrapservers());
    prop.setProperty("acks", getAcks());
    prop.setProperty("retries", getRetries());
    prop.setProperty("batch.size", getBatchsize());
    prop.setProperty("linger.ms", getLingerms());
    prop.setProperty("buffer.memory", getBuffermemory());
    prop.setProperty("key.serializer", getKeyserializer());
    prop.setProperty("value.serializer", getValueserializer());

    return prop;

  }

  /**
   * @return topic
   */
  public String getTopic() {
    // @Value("${kafka.producer.topic}")

    return environment.getProperty("kafka.producer.topic");
  }

  @Value("${kafka.producer.client.id}")
  private String clientId;

  @Value("${kafka.producer.bootstrap.servers}")
  private String bootstrapservers;

  @Value("${kafka.producer.acks}")
  private String acks;

  @Value("${kafka.producer.retries}")
  private String retries;

  @Value("${kafka.producer.batch.size}")
  private String batchsize;

  @Value("${kafka.producer.linger.ms}")
  private String lingerms;

  @Value("${kafka.producer.buffer.memory}")
  private String buffermemory;

  @Value("${kafka.producer.key.serializer}")
  private String keyserializer;

  @Value("${kafka.producer.value.serializer}")
  private String valueserializer;

  @Value("${kafka.producer.topic}")
  private String topic;

}
