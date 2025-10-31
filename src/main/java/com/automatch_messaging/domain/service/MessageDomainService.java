package com.automatch_messaging.domain.service;

import com.automatch_messaging.domain.model.Message;
import com.automatch_messaging.domain.model.MessageStatus;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class MessageDomainService {

    public Message prepareNewMessage(Message msg) {
        // Reglas básicas de dominio
        if (msg.getContent() == null || msg.getContent().isBlank()) {
            throw new IllegalArgumentException("El contenido del mensaje no puede ser vacío.");
        }
        msg.setStatus(MessageStatus.SENT);
        msg.setDeliveredAt(null);
        msg.setReadAt(null);
        return msg;
    }

    public Message markAsRead(Message msg) {
        msg.setStatus(MessageStatus.READ);
        msg.setReadAt(OffsetDateTime.now());
        return msg;
    }
}