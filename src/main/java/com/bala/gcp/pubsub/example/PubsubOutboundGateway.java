package com.bala.gcp.pubsub.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;

@Configuration
@MessagingGateway(defaultRequestChannel = "pubsubOutputChannel")
public interface PubsubOutboundGateway {
    void sendToPubsub(String text);
}