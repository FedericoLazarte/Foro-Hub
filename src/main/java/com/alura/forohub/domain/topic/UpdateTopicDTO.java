package com.alura.forohub.domain.topic;

import jakarta.validation.constraints.NotBlank;

public record UpdateTopicDTO(
        String title,
        String message,
        String author,
        String course
) {
}