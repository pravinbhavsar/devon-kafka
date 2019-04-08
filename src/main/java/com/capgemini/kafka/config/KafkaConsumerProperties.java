package com.capgemini.kafka.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 * class reads consumer properties from application.properties as part of POC In actual project Implementation move
 * these to separate location Basic minimum set of properties are considered for POC Current configuration supports
 * plaintext protocol and not SSL * @author pravbhav
 *
 */
@Configuration
public class KafkaConsumerProperties implements EnvironmentAware {

  @Value("${kafka.consumer.session.timeout.ms}")
  private String timeoutms;

  @Value("${kafka.consumer.group.id}")
  private String groupId;

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

  @Value("${kafka.consumer.enable.auto.commit}")
  private String autocommit;

  @Value("${kafka.consumer.auto.commit.interval}")
  private int autocommitInterval;

  static Environment environment;

  @Override
  public void setEnvironment(Environment environment) {
   KafkaConsumerProperties.environment = environment;
  }

  /**
   * @return environment
   */
  public static Environment getEnvironment() {

    return environment;
  }

  public String getKeydeserializer() {
    return environment.getProperty("kafka.consumer.key.deserializer").trim();

  }

  public void setKeydeserializer(String keydeserializer) {

    this.keydeserializer = keydeserializer;
  }

  public String getValuedeserializer() {

    return environment.getProperty("kafka.consumer.value.deserializer").trim();

  }

  public void setValuedeserializer(String valuedeserializer) {

    this.valuedeserializer = valuedeserializer;
  }
  


  /*
   * Return the instance of Properties class for Consumer object to be created properties values currently loaded from
   * application properties
   */
  public Properties getProperties() {

    Properties prop = new Properties();
  //  prop.put("consumer.enable.auto.commit", this.getAutocommit());
  //  prop.put("auto.commit.interval.ms", this.getAutocommitInterval());
    prop.put("session.timeout.ms", this.getTimeoutms());
    prop.put("group.id", this.getGroupId());
    prop.put("key.deserializer", this.getKeydeserializer());
    prop.put("value.deserializer", this.getValuedeserializer());
    prop.put("client.id", this.getClientId());
    prop.put("bootstrap.servers", this.getBootstrapservers());
    return prop;

  }

  /**
   * @return topic
   */
  public String getTopic() {
    return environment.getProperty("kafka.consumer.topic").trim();
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

    return environment.getProperty("kafka.consumer.client.id").trim();
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
    return environment.getProperty("kafka.consumer.bootstrap.servers").trim();
  }

  /**
   * @param bootstrapservers new value of {@link #getbootstrapservers}.
   */
  public void setBootstrapservers(String bootstrapservers) {
    this.bootstrapservers = bootstrapservers;
  }

  /**
   * @return buffermemory
   */
  public String getBuffermemory() {

    return environment.getProperty("kafka.consumer.buffer.memory").trim();

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
  public String getAutocommit() {

    return environment.getProperty("kafka.consumer.enable.auto.commit").trim();

  }

  /**
   * @param autocommit new value of {@link #getautocommit}.
   */
  public void setAutocommit(String autocommit) {

    this.autocommit = autocommit;
  }

  /**
   * @return autocommitInterval
   */
  public int getAutocommitInterval() {

    String interval = environment.getProperty("kafka.consumer.auto.commit.interval");
    int inval = Integer.parseInt(interval);
    return inval ;
  }

  /**
   * @param autocommitInterval new value of {@link #getautocommitInterval}.
   */
  public void setAutocommitInterval(int autocommitInterval) {

    this.autocommitInterval = autocommitInterval;
  }

  /**
   * @param timeoutms new value of {@link #gettimeoutms}.
   */
  public void setTimeoutms(String timeoutms) {

    this.timeoutms = timeoutms;
  }

  /**
   * @param groupId new value of {@link #getgroupId}.
   */
  public void setGroupId(String groupId) {

    this.groupId = groupId;
  }

  /**
   * @return timeoutms
   */
  public String getTimeoutms() {

    return environment.getProperty("kafka.consumer.session.timeout.ms").trim();
  }

  /**
   * @return groupId
   */
  public String getGroupId() {

    return environment.getProperty("kafka.consumer.group.id").trim();

  }

}
