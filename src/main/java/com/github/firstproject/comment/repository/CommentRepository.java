package com.github.firstproject.comment.repository;

import com.github.firstproject.comment.entity.Comment;
import com.github.firstproject.post.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByPostEntity(PostEntity post);
}