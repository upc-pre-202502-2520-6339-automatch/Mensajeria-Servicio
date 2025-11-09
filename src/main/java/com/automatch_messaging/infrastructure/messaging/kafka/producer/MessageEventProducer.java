package com.automatch_messaging.infrastructure.messaging.kafka.producer;

import com.automatch_messaging.domain.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void publishMessageCreated(Message msg) {
        String payload = String.format(
                "{\"messageId\": %d, \"conversationId\": %d, \"senderId\": %d, \"receiverId\": %d}",
                msg.getId(), msg.getConversationId(), msg.getSenderId(), msg.getReceiverId());
        kafkaTemplate.send("message.created", payload);
    }
}