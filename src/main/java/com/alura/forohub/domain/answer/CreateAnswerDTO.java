package com.alura.forohub.domain.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAnswerDTO(
        @NotBlank
        String message,
        @NotBlank
        String author,
        @NotNull
        Long topicId
) {
}
