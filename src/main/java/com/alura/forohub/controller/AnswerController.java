package com.alura.forohub.controller;

import com.alura.forohub.domain.answer.*;
import com.alura.forohub.domain.topic.Status;
import com.alura.forohub.domain.topic.Topic;
import com.alura.forohub.domain.topic.TopicRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/answers")
@SecurityRequirement(name = "bearer-key")
public class AnswerController {
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private TopicRepository topicRepository;

    @PostMapping
    public ResponseEntity<DetailsAnswerDTO> createAnswer(@RequestBody @Valid CreateAnswerDTO answerDTO,
                                               UriComponentsBuilder uriComponentsBuilder) {
        Topic topic = topicRepository.findByIdAndStatus(answerDTO.topicId(), Status.ACTIVE)
                .orElseThrow(() -> new EntityNotFoundException("The topic must exist and be active"));
        Answer answer = answerRepository.save(new Answer(answerDTO.message(), answerDTO.author(), topic));
        URI uri = uriComponentsBuilder.path("/answers/{id}").buildAndExpand(answer.getId()).toUri();
        DetailsAnswerDTO detailsAnswerDTO = new DetailsAnswerDTO(answer);
        return ResponseEntity.created(uri).body(detailsAnswerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
        if (!answerRepository.existsById(id))
            return ResponseEntity.notFound().build();
        answerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetailsAnswerDTO> updateAnswer(@PathVariable Long id, @RequestBody UpdateAnswerDTO answerDTO) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Answer not found"));
        Topic topic = answer.getTopic();
        if (topic.getStatus() != Status.ACTIVE)
            return ResponseEntity.badRequest().build();
        answer.update(answerDTO);
        return ResponseEntity.ok(new DetailsAnswerDTO(answer));

    }
}
