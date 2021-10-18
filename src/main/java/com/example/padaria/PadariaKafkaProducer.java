package com.example.padaria;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class PadariaKafkaProducer {
    private final String topic;
    // Key and Padaria are avro classes
    private final KafkaTemplate<Key, Padaria> template;

    public PadariaKafkaProducer(@Value("${kafka.topics.padaria-event}") String topic, KafkaTemplate<Key, Padaria> template) {
        this.topic = topic;
        this.template = template;
    }

    public void send(Padaria padaria) throws ExecutionException, InterruptedException {
        final Key key = Key.newBuilder()
                .setValue(padaria.getPadariaId())
                .build();

        template.send(topic, key, padaria).get();
    }
}
