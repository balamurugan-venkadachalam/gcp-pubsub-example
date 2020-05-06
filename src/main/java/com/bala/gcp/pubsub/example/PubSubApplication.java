package com.bala.gcp.pubsub.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PubSubApplication {

    public static void main(String[] args) throws IOException {
    SpringApplication.run(PubSubApplication.class, args);
  }

}