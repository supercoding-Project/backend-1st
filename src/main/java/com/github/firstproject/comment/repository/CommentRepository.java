package com.github.firstproject.comment.repository;

import com.github.firstproject.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
<<<<<<< HEAD
import java.util.Optional;
=======
>>>>>>> 81a22f91a9f3c714c3e5f6d3cc16e82dc7080521

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPostId(Integer postId);
<<<<<<< HEAD
}
=======
}
>>>>>>> 81a22f91a9f3c714c3e5f6d3cc16e82dc7080521
