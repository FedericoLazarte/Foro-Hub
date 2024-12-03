package com.alura.forohub.domain.topic;

import com.alura.forohub.domain.answer.Answer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Topic")
@Table(name = "topics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String title;

    @Column(nullable = false, unique = true, length = 250)
    private String message;

    @CreationTimestamp
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false, length = 100)
    private String author;

    @Column(nullable = false, length = 100)
    private String course;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;

    public Topic(CreateTopicDTO data) {
        this.title = data.title();
        this.message = data.message();
        this.creationDate = LocalDateTime.now();
        this.status = Status.ACTIVE;
        this.author = data.author();
        this.course = data.course();
        this.answers = new ArrayList<>();
    }

    public void update(UpdateTopicDTO topicDTO) {
        if (topicDTO.title() != null)
            this.title = topicDTO.title();
        if (topicDTO.message() != null)
            this.message = topicDTO.message();
        if (topicDTO.author() != null)
            this.author = topicDTO.author();
        if (topicDTO.course() != null)
            this.course = topicDTO.course();
    }

    public void close() {
        this.status = Status.INACTIVE;
    }

    public void addAnswer(Answer answer) {
        if (answers == null) {
            answers = new ArrayList<>();
        }
        answers.add(answer);
        answer.setTopic(this);
    }

    public void removeAnswer(Answer answer) {
        if (answers != null) {
            answers.remove(answer);
            answer.setTopic(null);
        }
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
        if (answers != null) {
            answers.forEach(answer -> answer.setTopic(this));
        }
    }
}
