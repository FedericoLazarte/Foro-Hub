package com.alura.forohub.domain.answer;

import jakarta.validation.constraints.NotBlank;

public record UpdateAnswerDTO(
        @NotBlank
        String message) {
}
