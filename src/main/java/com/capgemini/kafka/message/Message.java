package com.capgemini.kafka.message;

import java.io.Serializable;
/*
 *  Simple message class if you  dont need headers and complex object classes as messages
 *  if you are ok with key and value both as string
 *
 *  There is no consumer written for this message.
 *
 */

public class Message implements Serializable {

  private String Key;

  private String Value;

  private int partition = -1;

  private String topic;

  private long timestamp = -1;

  private long offset;

  /**
   * @return key
   */
  public String getKey() {

    return this.Key;
  }

  /**
   * @param key new value of {@link #getkey}.
   */
  public void setKey(String key) {

    this.Key = key;
  }

  /**
   * @return value
   */
  public String getValue() {

    return this.Value;
  }

  /**
   * @param value new value of {@link #getvalue}.
   */
  public void setValue(String value) {

    this.Value = value;
  }

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

  @Override
  public String toString() {

    return "Message {Key=" + this.Key + ", Value=" + this.Value + ", partition=" + this.partition + ", topic="
        + this.topic + ", timestamp=" + this.timestamp + ", offset=" + this.offset + "}";
  }
}
