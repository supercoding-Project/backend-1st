package com.github.firstproject.comment.controller;

import com.github.firstproject.comment.dto.CommentDto;
import com.github.firstproject.comment.entity.Comment;
import com.github.firstproject.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentDto>> getComments(@RequestParam Integer postId){
        List<CommentDto> comments = commentService.getComments(postId);
        return ResponseEntity.ok(comments);
    }
}
