package com.github.firstproject.comment.controller;

import com.github.firstproject.auth.entity.UserEntity;
import com.github.firstproject.auth.repository.UserRepository;
import com.github.firstproject.comment.dto.CommentDto;
import com.github.firstproject.comment.dto.CreateCommentDto;
import com.github.firstproject.comment.dto.UpdateCommentDto;
import com.github.firstproject.comment.entity.Comment;
import com.github.firstproject.comment.service.CommentService;
import com.github.firstproject.global.config.auth.custom.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final UserRepository userRepository;
    @Operation(summary = "댓글 조회")
    @GetMapping
    public ResponseEntity<List<CommentDto>> getComments(
            @Parameter(name = "post-id", description = "게시물 ID",example = "1")
            @RequestParam Integer postId){
        List<CommentDto> comments = commentService.getComments(postId);
        return ResponseEntity.ok(comments);
    }
    @Operation(summary = "댓글 추가")
    @PostMapping
    public ResponseEntity<Map<String,String>> addComment(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody CreateCommentDto createCommentDto){
        UserEntity user = customUserDetails.getUserEntity();
        Boolean isOk = commentService.addComment(createCommentDto,user);
        Map<String,String> response = new HashMap<>();
        if(isOk){
            response.put("messeage","댓글이 성공적으로 작성되었습니다.");
            return ResponseEntity.ok(response);
        }
        else {
            response.put("message","댓글 저장에 실패했습니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

    }
    @Operation(summary = "댓글 수정")
    @PutMapping("/{comment_id}")
    public ResponseEntity<Map<String,String>> updateComment(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Parameter(name = "comment_id", description = "댓글 ID", example = "1")
            @PathVariable Integer comment_id,
            @RequestBody UpdateCommentDto updateCommentDto
            ){
        UserEntity user = customUserDetails.getUserEntity();
        Boolean isOk = commentService.updateComment(comment_id,updateCommentDto,user);
        Map<String,String> response = new HashMap<>();
        if(isOk){
            response.put("message","댓글이 성공적으로 수정되었습니다.");
            return ResponseEntity.ok(response);
        }
        else {
            response.put("message","댓글 수정에 실패했습니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/{comment_id}")
    public ResponseEntity<Map<String,String>> deleteComment(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Parameter(name = "comment_id", description = "댓글 ID", example = "")
            @PathVariable Integer comment_id
    ){
        UserEntity user = customUserDetails.getUserEntity();
        Boolean isOk = commentService.deleteComment(comment_id,user);
        Map<String,String> response = new HashMap<>();
        if(isOk){
            response.put("message","댓글이 성공적으로 삭제되었습니다.");
            return ResponseEntity.ok(response);
        }
        else {
            response.put("message","댓글 삭제에 실패했습니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

    }

}
