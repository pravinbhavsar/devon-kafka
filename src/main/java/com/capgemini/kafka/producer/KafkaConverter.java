package com.capgemini.kafka.producer;

import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * @author pravbhav
 *
 */
public class KafkaConverter {

  /**
   * Convert Object from RecordMetadata to POJO object
   *
   * @param metadata
   * @return
   */
  public KafkaRecordMetaData convertKafkaMetaData(RecordMetadata metadata) {

    if (metadata == null) {

      return null;
    } else {
      KafkaRecordMetaData kmetadata = new KafkaRecordMetaData();
      kmetadata.setTopic(metadata.topic());
      kmetadata.setPartition(metadata.partition());
      kmetadata.setOffset(metadata.offset());
      kmetadata.setKeySize(metadata.serializedKeySize());
      kmetadata.setValueSize(metadata.serializedValueSize());
      kmetadata.setTimestamp(metadata.timestamp());
      return kmetadata;
    }
  }

}
