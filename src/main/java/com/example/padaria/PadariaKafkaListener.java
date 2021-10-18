package com.example.padaria;

import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class PadariaKafkaListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(PadariaCentreListener.class);

    private final PadariaEventProcessingService service;

    public PadariaKafkaListener(PadariaEventProcessingService service) {
        this.service = service;
    }

    @KafkaListener(topics = "${kafka.topics.padaria-event}")
    public void consume(ConsumerRecord<Key, DomainEventMessage> consumerRecord) {
        final Key key = consumerRecord.key();
        final DomainEventMessage eventMessage = consumerRecord.value();

        MDC.put("uuid", key.getValue());
        LOGGER.info("Received message {}", eventMessage);
        try {
            service.processEvent(eventMessage.getDomainObject());

            LOGGER.info("Event processed successfully");
        } catch (Exception e) {
            LOGGER.error("Error processing the padaria event", e);
        } finally {
            MDC.remove("uuid");
        }
    }
}
