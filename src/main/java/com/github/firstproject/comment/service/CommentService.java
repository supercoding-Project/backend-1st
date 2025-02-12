package com.github.firstproject.comment.service;

import com.github.firstproject.comment.dto.CommentDto;
import com.github.firstproject.comment.entity.Comment;
import com.github.firstproject.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
}
