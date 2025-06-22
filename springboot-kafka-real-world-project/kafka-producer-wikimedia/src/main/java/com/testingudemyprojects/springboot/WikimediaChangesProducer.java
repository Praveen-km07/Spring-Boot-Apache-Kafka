package com.testingudemyprojects.springboot;


import com.launchdarkly.eventsource.EventSource;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;


@Service
public class WikimediaChangesProducer
{
         private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);

     private KafkaTemplate<String,String> kafkaTemplate;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    String topic = "wikimedia_recentchange";

    // To read real-time stream data from Wikimedia, we use EventListener
    EventListener eventListener = new WikimediaChangesHandler(kafkaTemplate, topic);

    String url = "https://stream.wikimedia.org/v2/stream/recentchange";
    URI uri = URI.create(url);

    OkHttpClient client = new OkHttpClient.Builder()
            .eventListener(eventListener)
            .build();

    EventSource eventSource = new EventSource.Builder(uri)
            .client(client)
            .build();

//
//    public void sendMessage(){
//
//
////        BackgroundEventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate,topic);
////        String url ="https://stream.wikimedia.org/v2/stream/recentchange";
////
////        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
////       EventSource eventSource = builder.build();
//    }
}
