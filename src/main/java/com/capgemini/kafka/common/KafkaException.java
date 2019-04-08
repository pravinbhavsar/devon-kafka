package com.capgemini.kafka.common;

/**
 * @author pravbhav
 *
 */
public class KafkaException extends Exception {

  public KafkaException(String errormsg) {
    super(errormsg);

  }
}
