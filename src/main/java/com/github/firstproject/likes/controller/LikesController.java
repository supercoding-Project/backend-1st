package com.github.firstproject.likes.controller;

import com.github.firstproject.auth.entity.UserEntity;
import com.github.firstproject.global.config.auth.custom.CustomUserDetails;
import com.github.firstproject.likes.dto.LikesDto;
import com.github.firstproject.likes.service.LikesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class LikesController {

    private final LikesService likesService;

    @PostMapping("/add_like")
    public ResponseEntity<?> like(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody @Valid LikesDto likesDto) throws Exception {
        UserEntity userEntity = customUserDetails.getUserEntity();
        log.info("[POST] 좋아요");

        boolean isLiked = likesService.addLike(userEntity ,likesDto.getComment_id());

        if (isLiked) {
            return ResponseEntity.ok("좋아요 추가 성공");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 좋아요를 누른 댓글입니다.");
        }
    }

    @DeleteMapping("/delete_like")
    public ResponseEntity<?> deleteLike(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody @Valid LikesDto likesDto) {
        UserEntity userEntity = customUserDetails.getUserEntity();
        log.info("[POST] 좋아요 취소");

        boolean isDeleted = likesService.removeLike(userEntity ,likesDto.getComment_id());

        if (isDeleted) {
            return ResponseEntity.ok("좋아요 취소 성공");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("좋아요 취소 실패: 해당 댓글에 좋아요가 없습니다.");
        }
    }
}