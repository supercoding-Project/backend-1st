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
import org.webjars.NotFoundException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LikesController {

    private final LikesService likesService;

    @PostMapping("/add_like")
    public ResponseEntity<?> addLike(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody @Valid LikesDto likesDto) throws NotFoundException {
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
            @RequestBody @Valid LikesDto likesDto) throws NotFoundException {
        UserEntity userEntity = customUserDetails.getUserEntity();
        log.info("[DELETE] 좋아요 취소");

        try {
            boolean isDeleted = likesService.deleteLike(userEntity, likesDto.getComment_id());

            if (isDeleted) {
                return ResponseEntity.ok("좋아요 취소 성공");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 댓글에 좋아요가 없습니다.");
            }
        } catch (Exception e) {
            log.error("좋아요 취소 중 오류 발생: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("서버 오류가 발생했습니다. 다시 시도해주세요.");
        }
    }
}