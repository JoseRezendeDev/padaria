package com.example.padaria;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutionException;

public class PadariaEventProcessingService {
    @Autowired
    private PadariaKafkaProducer padariaKafkaProducer;

    public void processEvent(Object padaria) throws ExecutionException, InterruptedException {
        padariaKafkaProducer.send(padaria);
    }
}
