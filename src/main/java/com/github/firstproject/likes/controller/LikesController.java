package com.github.firstproject.likes.controller;

import com.github.firstproject.likes.dto.LikesDto;
import com.github.firstproject.likes.service.LikesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikesController {

    private final LikesService likesService;

    @PostMapping("/add_like")
    public ResponseEntity<?> like(@RequestBody LikesDto likesDto) {
        boolean isLiked = likesService.addLike(likesDto.getUsername(), likesDto.getCommentId());

        if (isLiked) {
            return ResponseEntity.ok("좋아요 추가 성공");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 좋아요를 누른 댓글입니다.");
        }
    }

    @DeleteMapping("/delete_like")
    public ResponseEntity<?> deleteLike(@RequestBody LikesDto likesDto) {
        boolean isDeleted = likesService.removeLike(likesDto.getUsername(), likesDto.getCommentId());

        if (isDeleted) {
            return ResponseEntity.ok("좋아요 취소 성공");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("좋아요 취소 실패: 해당 댓글에 좋아요가 없습니다.");
        }
    }
}
