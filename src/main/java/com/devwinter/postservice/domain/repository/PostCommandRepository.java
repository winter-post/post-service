package com.devwinter.postservice.domain.repository;

import com.devwinter.postservice.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostCommandRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByMemberIdAndTitleAndCreatedAtBetween(Long memberId, String title, LocalDateTime startDate, LocalDateTime endDate);
}
