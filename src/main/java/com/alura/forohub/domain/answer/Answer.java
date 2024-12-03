package com.alura.forohub.domain.answer;

import com.alura.forohub.domain.topic.Topic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Answer")
@Table(name = "answers")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 250)
    private String message;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    public Answer(String message, String author, Topic topic) {
        this.message = message;
        this.author = author;
        this.topic = topic;
        this.creationDate = LocalDateTime.now();
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void update(UpdateAnswerDTO answerDTO) {
        if (answerDTO != null) {
            this.message = answerDTO.message();
            this.creationDate = LocalDateTime.now();
        }
    }
}

