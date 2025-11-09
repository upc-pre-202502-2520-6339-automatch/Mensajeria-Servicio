package com.automatch_messaging.infrastructure.messaging.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InteractionEventConsumer {

    @KafkaListener(topics = "interaction.created", groupId = "mensajeria-service-group")
    public void listenInteractionCreated(String message) {
        log.info("Mensajeria recibió evento interaction.created: {}", message);
        // aquí puedes llamar tu ApplicationService si quieres procesar
    }
}