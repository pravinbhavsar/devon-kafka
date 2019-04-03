package com.capgemini.kafka.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * class reads consumer properties from application.properties as part of POC In actual project Implementation move
 * these to separate location Basic minimum set of properties are considered for POC Current configuration supports
 * plaintext protocol and not SSL * @author pravbhav
 *
 */
@Configuration
public class KafkaConsumerProperties implements EnvironmentAware {

  static Environment environment;

  @Override
  public void setEnvironment(Environment environment) {

    KafkaConsumerProperties.environment = environment;
  }

  /*
   * @Bean public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
   *
   * return new PropertySourcesPlaceholderConfigurer(); }
   */

  /**
   * @return environment
   */
  public static Environment getEnvironment() {

    return environment;
  }

  public String getKeydeserializer() {

    return this.keydeserializer;
  }

  public void setKeydeserializer(String keydeserializer) {

    this.keydeserializer = keydeserializer;
  }

  public String getValuedeserializer() {

    return this.valuedeserializer;
  }

  public void setValuedeserializer(String valuedeserializer) {

    this.valuedeserializer = valuedeserializer;
  }

  /*
   * Return the instance of Properties class for Consumer object to be created
   *
   */
  public Properties getProperties() {

    Properties prop = new Properties();
    prop.put("client.id", this.clientId);
    prop.put("bootstrap.servers", this.bootstrapservers);
    prop.put("consumer.enable.auto.commit", this.autocommit);
    prop.put("auto.commit.interval.ms", this.autocommitInterval);
    prop.put("session.timeout.ms", this.batchsize);
    prop.put("group.id", this.lingerms);
    prop.put("key.serializer", getKeydeserializer());
    prop.put("value.serializer", getValuedeserializer());

    return prop;

  }

  /**
   * @return topic
   */
  public String getTopic() {

    return this.topic;
  }

  /**
   * @param topic new value of {@link #gettopic}.
   */
  public void setTopic(String topic) {

    this.topic = topic;
  }

  /**
   * @return clientId
   */
  public String getClientId() {

    return this.clientId;
  }

  /**
   * @param clientId new value of {@link #getclientId}.
   */
  public void setClientId(String clientId) {

    this.clientId = clientId;
  }

  /**
   * @return bootstrapservers
   */
  public String getBootstrapservers() {

    return this.bootstrapservers;
  }

  /**
   * @param bootstrapservers new value of {@link #getbootstrapservers}.
   */
  public void setBootstrapservers(String bootstrapservers) {

    this.bootstrapservers = bootstrapservers;
  }

  /**
   * @return batchsize
   */
  public String getBatchsize() {

    return this.batchsize;
  }

  /**
   * @param batchsize new value of {@link #getbatchsize}.
   */
  public void setBatchsize(String batchsize) {

    this.batchsize = batchsize;
  }

  /**
   * @return lingerms
   */
  public String getLingerms() {

    return this.lingerms;
  }

  /**
   * @param lingerms new value of {@link #getlingerms}.
   */
  public void setLingerms(String lingerms) {

    this.lingerms = lingerms;
  }

  /**
   * @return buffermemory
   */
  public String getBuffermemory() {

    return this.buffermemory;
  }

  /**
   * @param buffermemory new value of {@link #getbuffermemory}.
   */
  public void setBuffermemory(String buffermemory) {

    this.buffermemory = buffermemory;
  }

  /**
   * @return autocommit
   */
  public boolean isAutocommit() {

    return this.autocommit;
  }

  /**
   * @param autocommit new value of {@link #getautocommit}.
   */
  public void setAutocommit(boolean autocommit) {

    this.autocommit = autocommit;
  }

  /**
   * @return autocommitInterval
   */
  public int getAutocommitInterval() {

    return this.autocommitInterval;
  }

  /**
   * @param autocommitInterval new value of {@link #getautocommitInterval}.
   */
  public void setAutocommitInterval(int autocommitInterval) {

    this.autocommitInterval = autocommitInterval;
  }

  @Value("${kafka.consumer.enable.auto.commit}")
  private boolean autocommit;

  @Value("${kafka.consumer.auto.commit.interval}")
  private int autocommitInterval;

  @Value("${kafka.consumer.session.timeout.ms}")
  private String batchsize;

  @Value("${kafka.consumer.group.id}")
  private String lingerms;

  @Value("${kafka.consumer.buffer.memory}")
  private String buffermemory;

  @Value("${kafka.consumer.key.deserializer}")
  private String keydeserializer;

  @Value("${kafka.consumer.value.deserializer}")
  private String valuedeserializer;

  @Value("${kafka.consumer.topic}")
  private String topic;

  @Value("${kafka.consumer.client.id}")
  private String clientId;

  @Value("${kafka.consumer.bootstrap.servers}")
  private String bootstrapservers;

}
