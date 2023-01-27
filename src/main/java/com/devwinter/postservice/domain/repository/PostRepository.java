package com.devwinter.postservice.domain.repository;

import com.devwinter.postservice.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
