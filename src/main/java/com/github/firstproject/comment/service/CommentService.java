package com.github.firstproject.comment.service;

import com.github.firstproject.auth.entity.UserEntity;
import com.github.firstproject.comment.dto.CommentDto;
import com.github.firstproject.comment.dto.CreateCommentDto;
import com.github.firstproject.comment.entity.Comment;
import com.github.firstproject.comment.repository.CommentRepository;
import com.github.firstproject.global.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    public List<CommentDto> getComments(Integer postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment ->
                CommentDto.builder()
                        .id(comment.getComment_id())
                        .content(comment.getContent())
                        .author(comment.getUserEntity().getUsername())
                        .postId(comment.getPostId())
                        .createdAt(comment.getCreatedAt())
                        .build()).collect(Collectors.toList());

    }
    @Transactional
    public Boolean addComment(CreateCommentDto createCommentDto, UserEntity userEntity) {
        //save
        Boolean isSuccess = false;
        try {
            commentRepository.save(
                    Comment.builder()
                            .content(createCommentDto.getContent())
                            .userEntity(userEntity)
                            .postId(createCommentDto.getPostId())
                            .createdAt(LocalDateTime.now()).build()
            );
        } catch (Exception e) {
            isSuccess = false;
        }
        return isSuccess;
    }
}