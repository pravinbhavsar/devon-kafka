package com.capgemini.kafka.common;

/**
 * @author pravbhav
 *
 *         common utils for Kafka Integration
 *
 */
public class Kafkautil {

  public static boolean isNullOrEmpty(String str) {

    if (str != null && !str.isEmpty())
      return false;
    return true;
  }

}
