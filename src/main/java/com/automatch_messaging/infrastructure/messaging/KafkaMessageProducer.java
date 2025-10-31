package com.automatch_messaging.infrastructure.messaging;

import com.automatch_messaging.domain.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaMessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic = "messaging.messages";

    public void publishMessageCreated(Message message) {
        // Simulación (no enviamos a Kafka aún)
        log.info(
                "🧪 [SIM-KAFKA] Publicar evento MESSAGE_CREATED a topic '{}': id={}, conversationId={}, senderId={}, receiverId={}",
                topic, message.getId(), message.getConversationId(), message.getSenderId(), message.getReceiverId());
    }
}