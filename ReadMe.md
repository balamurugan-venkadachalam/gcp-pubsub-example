## PubSub

Step 1: create topic first 
```
 gcloud pubsub topics create testTopic
```

Step 2: create subscription 
```
gcloud pubsub subscriptions create testSubscription --topic testTopic
```
Step 3: Set GOOGLE_APPLICATION_CREDENTIALS
        
There a few ways to "activate" application default credentials:

Use your user account

Run
``` 
gcloud auth application-default login
```
    Use service account (preferred), create service account assign required privilege and download key file.

set environment variable
```
GOOGLE_APPLICATION_CREDENTIALS=path/to/your/service_accont_key_file.json
copy path/to/your/service_accont_key_file.json to ~/.config/gcloud/application_default_credentials.json`
```
now below command should work.
```
gcloud auth application-default print-access-token
```


Step 4: run the project 

```
mvn spring-boot:run
```
## Commands
```
 gcloud pubsub subscriptions create testSubscription --topic testTopic
 gcloud pubsub subscriptions create testSubscription --topic testTopic
 gcloud pubsub topics publish testTopic --message 'message 1'
 gcloud pubsub subscriptions pull my-dead-letter-sub
 gcloud pubsub snapshots list
 gcloud pubsub snapshots create first-dead-letter-snapshot --subscription=my-dead-letter-sub 
 gcloud pubsub subscriptions seek my-dead-letter-sub  --snapshot=first-dead-letter-snapshot
 gcloud pubsub topics get-iam-policy my-topic
 gcloud pubsub subscriptions pull --auto-ack my-sub
 gcloud pubsub subscriptions update testSubscription --retain-acked-messages
```

 - [x] By default, a message that cannot be delivered within the maximum retention time of 7 days is deleted and is no longer accessible
 - [x] This typically happens when subscribers do not keep up with the flow of messages. Note that you can configure message retention duration (the range is from 10 minutes to 7 days)
 - [x] a message published to a topic that has no subscription will not be delivered to any subscriber
 - [x] By default, subscriptions expire after 31 days of inactivity (for instance, if there are no active connections, pull requests, or push successes).
 ## Monitoring
 - [x] subscription/num_undelivered_messages
 - [x] subscription/oldest_unacked_message_age 
 - [x] pubsub.googleapis.com/topic/byte_cost
 - [x] pubsub.googleapis.com/subscription/byte_cost
 
## Emulator

 gcloud components install pubsub-emulator
 gcloud beta emulators pubsub start
 
 After you start the emulator, you need to set environment variables so that your application connects to the emulator instead of Pub/Sub
 $(gcloud beta emulators pubsub env-init)

## Reference 
- [x] https://cloud.google.com/pubsub/docs/quickstart-client-libraries#java
- [x] https://github.com/googleapis/java-pubsub
- [x] https://cloud.google.com/pubsub/docs/quickstart-client-libraries#java
- [x] https://cloud.google.com/pubsub/docs/encryption
- [x] https://cloud.google.com/pubsub/docs/emulator
- [x] https://cloud.google.com/pubsub/architecture