package com.bala.gcp.pubsub.example;

import com.google.api.gax.rpc.AlreadyExistsException;
import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.PushConfig;

public class SubscriberExample {
  // use the default project id
  private static final String PROJECT_ID = ServiceOptions.getDefaultProjectId();

  static class MessageReceiverExample implements MessageReceiver {

    @Override
    public void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {
      System.out.println(
          "Message Id: " + message.getMessageId() + " Data: " + message.getData().toStringUtf8());
      // Ack only after all work for the message is complete.
      consumer.ack();
    }
  }

  /** Receive messages over a subscription. */
  public static void main(String... args) throws Exception {

    String topicId = "my-topic";
    String subscriptionId = "my-sub";

    ProjectTopicName topicName = ProjectTopicName.of(PROJECT_ID, topicId);
    ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(PROJECT_ID, subscriptionId);

    try (SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create()) {
      subscriptionAdminClient.createSubscription(subscriptionName, topicName, PushConfig.getDefaultInstance(), 0);
    }catch (AlreadyExistsException e){
      System.out.println(subscriptionId +" Subscription is already exits");
    }

    Subscriber subscriber = null;
    try {
      // create a subscriber bound to the asynchronous message receiver
      subscriber = Subscriber.newBuilder(subscriptionName, new MessageReceiverExample()).build();
      subscriber.startAsync().awaitRunning();
      // Allow the subscriber to run indefinitely unless an unrecoverable error occurs.
      subscriber.awaitTerminated();
    } catch (IllegalStateException e) {
      System.out.println("Subscriber unexpectedly stopped: " + e);
    }
  }
}