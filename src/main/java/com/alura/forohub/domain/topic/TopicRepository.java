package com.alura.forohub.domain.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    @Query("""
            SELECT t FROM Topic t
            WHERE t.status = :status
            ORDER BY t.creationDate DESC
            """)
    Page<Topic> findTop10ByStatusOrderByCreationDateDesc(@Param("status") Status status, Pageable pageable);
}
