package com.capgemini.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author pravbhav
 *
 */

@Configuration
@EnableSwagger2

public class KafkaSwaggerConfig {

  @Bean
  public Docket kafkaApi() {

    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("com.capgemini.kafka.service.impl"))
        .paths(PathSelectors.regex("/kafka.*")).build();
  }

}
