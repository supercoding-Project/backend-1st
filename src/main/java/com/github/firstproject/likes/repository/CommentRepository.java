package com.github.firstproject.likes.repository;

import com.github.firstproject.likes.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository  extends JpaRepository<Comment, Integer> {
    List<Comment> findByPostId(Integer postId);
}
