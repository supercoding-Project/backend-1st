package com.github.firstproject.likes.repository;

import com.github.firstproject.auth.entity.UserEntity;
import com.github.firstproject.likes.entity.LikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<LikesEntity, Integer> {
    Optional<LikesEntity> findByUserEntityAndComment(UserEntity userEntity, Comment comment);

    boolean countByComment(Comment comment);
}
