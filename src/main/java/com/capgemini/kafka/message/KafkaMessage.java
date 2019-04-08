package com.capgemini.kafka.message;

import java.io.Serializable;

/**
 *
 * Kafka Message with generic template Value is payload and can be any object key is message Id, please map to
 * co-relation id / message id if its JMS message Message is with header, toString needs to be overridden to send
 * details about messgae Message can include headers, especially for JMS, Angular messages etc.
 *
 */
public class KafkaMessage implements Serializable {

  private String messageId;

  private String key;

  private String payload;

  private String partition;

  private String topic;

  private String timestamp;

  private String offset;

  /**
   * @return offset
   */
  public String getOffset() {

    return this.offset;
  }

  /**
   * @param offset new value of {@link #getoffset}.
   */
  public void setOffset(String offset) {

    this.offset = offset;
  }

  /**
   * @return messageId
   */
  public String getMessageId() {

    return this.messageId;
  }

  /**
   * @param messageId new value of {@link #getmessageId}.
   */
  public void setMessageId(String messageId) {

    this.messageId = messageId;
  }

  /**
   * @return key
   */
  public String getKey() {

    return this.key;
  }

  /**
   * @param key new value of {@link #getkey}.
   */
  public void setKey(String key) {

    this.key = key;
  }

  /**
   * @return payload
   */
  public String getPayload() {

    return this.payload;
  }

  /**
   * @param payload new value of {@link #getpayload}.
   */
  public void setPayload(String payload) {

    this.payload = payload;
  }

  /**
   * @return partition
   */
  public String getPartition() {

    return this.partition;
  }

  /**
   * @param partition new value of {@link #getpartition}.
   */
  public void setPartition(String partition) {

    this.partition = partition;
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
   * @return timestamp
   */
  public String getTimestamp() {

    return this.timestamp;
  }

  /**
   * @param timestamp new value of {@link #gettimestamp}.
   */
  public void setTimestamp(String timestamp) {

    this.timestamp = timestamp;
  }

  @Override
  public String toString() {

    return "KafkaMessage [messageId=" + this.messageId + ", key=" + this.key + ", payload=" + this.payload
        + ", partition=" + this.partition + ", topic=" + this.topic + ", timestamp=" + this.timestamp + ", offset="
        + this.offset + "]";
  }

}
