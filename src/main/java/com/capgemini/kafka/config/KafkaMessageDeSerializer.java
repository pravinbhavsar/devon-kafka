package com.capgemini.kafka.config;

import java.util.Map;

import com.capgemini.kafka.message.KafkaMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * class is used to de serialize the object at consumer end. Currently entire object is deserielized, class can be
 * modified based on requirement based on message format, separate key and value deserializer can be used.
 *
 * @author pravbhav
 *
 */
public class KafkaMessageDeSerializer implements org.apache.kafka.common.serialization.Deserializer {

  @Override
  public void configure(Map map, boolean b) {

  }

  @Override
  public void close() {

  }

  @Override
  public KafkaMessage deserialize(String arg0, byte[] arg1) {

    ObjectMapper mapper = new ObjectMapper();
    KafkaMessage message = null;
    try {
      message = mapper.readValue(arg1, KafkaMessage.class);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return message;

  }

}
