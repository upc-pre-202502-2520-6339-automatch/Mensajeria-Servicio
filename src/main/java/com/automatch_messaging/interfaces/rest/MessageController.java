package com.automatch_messaging.interfaces.rest;

import com.automatch_messaging.application.dto.MessageRequestDTO;
import com.automatch_messaging.application.service.MessageApplicationService;
import com.automatch_messaging.domain.model.Message;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
@Tag(name = "Message Controller", description = "Mensajería entre comprador y vendedor")
public class MessageController {

    private final MessageApplicationService service;

    @PostMapping("/send")
    @Operation(summary = "Enviar un mensaje", description = "Crea y envía un nuevo mensaje dentro de una conversación.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(mediaType = "application/json", examples = @ExampleObject(name = "Ejemplo de envío", value = """
            {
              "conversationId": 1001,
              "senderId": 2001,
              "receiverId": 3001,
              "content": "Hola, ¿sigues interesado?"
            }
            """))))
    public ResponseEntity<Message> send(@Valid @RequestBody MessageRequestDTO dto) {
        return ResponseEntity.ok(service.send(dto));
    }

    @GetMapping
    @Operation(summary = "Listar mensajes por conversación")
    public ResponseEntity<List<Message>> list(@RequestParam Long conversationId) {
        return ResponseEntity.ok(service.listByConversation(conversationId));
    }

    @PatchMapping("/{id}/read")
    @Operation(summary = "Marcar un mensaje como leído")
    public ResponseEntity<Message> markAsRead(@PathVariable Long id) {
        return ResponseEntity.ok(service.markAsRead(id));
    }
}