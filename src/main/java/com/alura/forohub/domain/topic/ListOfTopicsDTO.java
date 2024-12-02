package com.alura.forohub.domain.topic;

import java.time.LocalDateTime;

public record ListOfTopicsDTO(
        String title,
        String message,
        LocalDateTime creationDate,
        Status status,
        String author,
        String course
) {
    public ListOfTopicsDTO(Topic topic) {
        this(
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getStatus(),
                topic.getAuthor(),
                topic.getCourse()
        );
    }
}
