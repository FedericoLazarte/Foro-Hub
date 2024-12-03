package com.alura.forohub.controller;

import com.alura.forohub.domain.answer.Answer;
import com.alura.forohub.domain.answer.AnswerRepository;
import com.alura.forohub.domain.answer.DetailsAnswerDTO;
import com.alura.forohub.domain.topic.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
public class TopicController {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @PostMapping
    public ResponseEntity<Topic> createTopic(@RequestBody @Valid CreateTopicDTO topicalRecord,
                                      UriComponentsBuilder uriComponentsBuilder) {
        Topic topic = topicRepository.save(new Topic(topicalRecord));
        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(url).body(topic);
    }

    @GetMapping
    public ResponseEntity<Page<ListOfTopicsDTO>> getLatestTopics(@PageableDefault(page = 0, size = 10)Pageable pagination) {
        return ResponseEntity.ok(topicRepository.findTop10ByStatusOrderByCreationDateDesc(Status.ACTIVE, pagination).map(ListOfTopicsDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailTopicDTO> getTopic(@PathVariable Long id) {
        Optional<Topic> optionalTopic = topicRepository.findByIdAndStatus(id, Status.ACTIVE);
        if (optionalTopic.isEmpty())
            return ResponseEntity.notFound().build();
        DetailTopicDTO detailTopic = new DetailTopicDTO(optionalTopic.get());
        return ResponseEntity.ok(detailTopic);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetailTopicDTO> updateTopic(@PathVariable @Valid Long id,
                                                      @RequestBody UpdateTopicDTO topicDTO) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        topic.update(topicDTO);
        return ResponseEntity.ok(new DetailTopicDTO(topic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        boolean exists = topicRepository.existsById(id);
        if (!exists)
            return ResponseEntity.notFound().build();
        topicRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/close/{id}")
    @Transactional
    public ResponseEntity<Void> closeTopic(@PathVariable Long id) {
        boolean exists = topicRepository.existsById(id);
        if (!exists)
            return ResponseEntity.notFound().build();
        Topic topic = topicRepository.getReferenceById(id);
        topic.close();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/answers")
    public ResponseEntity<Page<DetailsAnswerDTO>> getAnswerForTopic(@PathVariable Long id,
                                                                    @PageableDefault(size = 10) Pageable pageable) {
        Page<Answer> answerPage = answerRepository.findByTopicIdAndTopicStatus(id, Status.ACTIVE, pageable);
        if (answerPage.isEmpty())
            return ResponseEntity.notFound().build();
        Page<DetailsAnswerDTO> dtosPage = answerPage.map(DetailsAnswerDTO::new);
        return ResponseEntity.ok(dtosPage);
    }
}
