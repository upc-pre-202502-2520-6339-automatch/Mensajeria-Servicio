package com.automatch_messaging.application.service;

import com.automatch_messaging.application.dto.MessageRequestDTO;
import com.automatch_messaging.domain.model.Message;
import com.automatch_messaging.domain.repository.MessageRepository;
import com.automatch_messaging.domain.service.MessageDomainService;
import com.automatch_messaging.infrastructure.messaging.KafkaMessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageApplicationService {

    private final MessageRepository repository;
    private final MessageDomainService domainService;
    private final KafkaMessageProducer producer; // simulado por ahora

    @Transactional
    public Message send(MessageRequestDTO dto) {
        Message msg = Message.builder()
                .conversationId(dto.conversationId())
                .senderId(dto.senderId())
                .receiverId(dto.receiverId())
                .content(dto.content())
                .build();

        msg = domainService.prepareNewMessage(msg);
        Message saved = repository.save(msg);

        // Publica evento (simulado)
        producer.publishMessageCreated(saved);
        return saved;
    }

    @Transactional(readOnly = true)
    public List<Message> listByConversation(Long conversationId) {
        return repository.findByConversationIdOrderByCreatedAtAsc(conversationId);
    }

    @Transactional
    public Message markAsRead(Long messageId) {
        Message msg = repository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("Mensaje no encontrado: " + messageId));
        domainService.markAsRead(msg);
        return repository.save(msg);
    }
}