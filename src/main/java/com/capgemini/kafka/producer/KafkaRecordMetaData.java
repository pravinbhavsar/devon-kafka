package com.capgemini.kafka.producer;

/**
 * @author pravbhav
 *
 */
public class KafkaRecordMetaData {

  private String topic;

  private int partition;

  private long offset;

  int keySize;

  int valueSize;

  long timestamp;

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

  /**
   * @return keySize
   */
  public int getKeySize() {

    return this.keySize;
  }

  /**
   * @param keySize new value of {@link #getkeySize}.
   */
  public void setKeySize(int keySize) {

    this.keySize = keySize;
  }

  /**
   * @return valueSize
   */
  public int getValueSize() {

    return this.valueSize;
  }

  /**
   * @param valueSize new value of {@link #getvalueSize}.
   */
  public void setValueSize(int valueSize) {

    this.valueSize = valueSize;
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

  @Override
  public String toString() {

    return "KafkaRecordMetaData {topic=" + this.topic + ", partition=" + this.partition + ", offset=" + this.offset
        + ", keySize=" + this.keySize + ", valueSize=" + this.valueSize + ", timestamp=" + this.timestamp + "}";
  }

}
