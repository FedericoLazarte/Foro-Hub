package com.alura.forohub.domain.topic;

import jakarta.validation.constraints.NotBlank;

public record CreateTopicDTO(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotBlank
        String author,
        @NotBlank
        String course
) {
}
