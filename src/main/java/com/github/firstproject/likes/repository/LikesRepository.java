package com.github.firstproject.likes.repository;

import com.github.firstproject.likes.entity.LikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<LikesEntity, Integer> {

    Optional<LikesEntity> findByUserUsernameAndCommentPostId(String username, Integer postId);
}
