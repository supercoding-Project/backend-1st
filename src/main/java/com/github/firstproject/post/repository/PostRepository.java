package com.github.firstproject.post.repository;

import com.github.firstproject.post.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    @Query("SELECT p FROM PostEntity p JOIN FETCH p.userEntity u WHERE u.email = :searchEmail")
    Page <PostEntity> pagePostList(String searchEmail, Pageable pageable);
}
