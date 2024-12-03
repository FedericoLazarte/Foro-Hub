package com.alura.forohub.domain.answer;

import com.alura.forohub.domain.topic.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Page<Answer> findByTopicIdAndTopicStatus(Long topicId, Status status, Pageable pageable);
}
