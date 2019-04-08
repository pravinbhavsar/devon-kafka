package com.capgemini.kafka.config;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * class is used to serialize the object at Producer end. Currently entire object is serielized, class can be modified
 * based on requirement based on message format, separate key and value serializer can be used. This is configured as an
 * input in application.properties file.
 *
 * @author pravbhav
 *
 */
public class KafkaMessageSerializer implements org.apache.kafka.common.serialization.Serializer {

  public void configure(Map map, boolean b) {

  }

  @Override
  public byte[] serialize(String s, Object msg) {

    byte[] serializedBytes = null;
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      serializedBytes = objectMapper.writeValueAsString(msg).getBytes();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return serializedBytes;
  }

  public void close() {

  }

}
