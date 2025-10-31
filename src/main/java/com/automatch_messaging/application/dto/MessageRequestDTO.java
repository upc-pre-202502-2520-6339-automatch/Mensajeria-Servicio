package com.automatch_messaging.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MessageRequestDTO(
        @Schema(example = "1001") @NotNull @Min(1) Long conversationId,
        @Schema(example = "2001") @NotNull @Min(1) Long senderId,
        @Schema(example = "3001") @NotNull @Min(1) Long receiverId,
        @Schema(example = "Hola, ¿sigues interesado?") @NotBlank String content) {
}