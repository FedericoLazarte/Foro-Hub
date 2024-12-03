package com.alura.forohub.domain.answer;

import com.alura.forohub.domain.topic.Topic;

import java.time.LocalDateTime;

public record DetailsAnswerDTO(
        String message,
        String author,
        LocalDateTime creationDate
) {
    public DetailsAnswerDTO(Answer answer) {
        this(
                answer.getMessage(),
                answer.getAuthor(),
                answer.getCreationDate()
        );
    }
}
