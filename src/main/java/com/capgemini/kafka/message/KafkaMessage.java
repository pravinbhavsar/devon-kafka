package com.capgemini.kafka.message;

import java.io.Serializable;

/**
 *
 * Kafka Message with generic template Value is payload and can be any object key is message Id, please map to
 * co-relation id / message id if its JMS message Message is with header, toString needs to be overridden to send
 * details about messgae Message can include headers, especially for JMS, Angular messages etc.
 *
 */
public class KafkaMessage<T> implements Serializable {

  private int messageId;

  private T payload;

  private int partition;

  private String topic;

  private long timestamp;

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

  private String key;

  /**
   * @return timestamp
   */
  public long getTimestamp() {

    return this.timestamp;
  }

  /**
   * @param timestamp new value of {@link #gettimestamp}.
   */
  public void setTimestamp(long timestamp) {

    this.timestamp = timestamp;
  }

  /**
   * @return offset
   */
  public long getOffset() {

    return this.offset;
  }

  /**
   * @param offset new value of {@link #getoffset}.
   */
  public void setOffset(long offset) {

    this.offset = offset;
  }

  private long offset;

  /**
   * @return partition
   */
  public int getPartition() {

    return this.partition;
  }

  /**
   * @param partition new value of {@link #getpartition}.
   */
  public void setPartition(int partition) {

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
   * @return messageId
   */
  public int getMessageId() {

    return this.messageId;
  }

  /**
   * @param messageId new value of {@link #getmessageId}.
   */
  public void setMessageId(int messageId) {

    this.messageId = messageId;
  }

  /**
   * @return payload
   */
  public T getPayload() {

    return this.payload;
  }

  /**
   * @param payload new value of {@link #getpayload}.
   */
  public void setPayload(T payload) {

    this.payload = payload;
  }

  @Override
  public String toString() {

    return "KafkaMessage(" + " " + this.messageId + "  , " + this.payload.toString() + ")";
  }

}
