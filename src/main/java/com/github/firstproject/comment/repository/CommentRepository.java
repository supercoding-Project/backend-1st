package com.github.firstproject.comment.repository;

import com.github.firstproject.comment.entity.Comment;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPostEntity(PostEntity post);
}
