package com.alura.forohub.controller;

import com.alura.forohub.domain.topic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    private TopicRepository topicRepository;

    @PostMapping
    public ResponseEntity createTopic(@RequestBody CreateTopicDTO topicalRecord,
                                      UriComponentsBuilder uriComponentsBuilder) {
        Topic topic = topicRepository.save(new Topic(topicalRecord));
        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(url).body(topic);
    }

    @GetMapping
    public ResponseEntity<Page<ListOfTopicsDTO>> getLatestTopics(@PageableDefault(page = 0, size = 10)Pageable pagination) {
        return ResponseEntity.ok(topicRepository.findTop10ByStatusOrderByCreationDateDesc(Status.ACTIVE, pagination).map(ListOfTopicsDTO::new));
    }


}
