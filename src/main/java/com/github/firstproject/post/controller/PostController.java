package com.github.firstproject.post.controller;

import com.github.firstproject.global.config.auth.custom.CustomUserDetails;
import com.github.firstproject.global.dto.MsgResponseDto;
import com.github.firstproject.post.dto.PostDto;
import com.github.firstproject.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시판 전체조회", description = "게시물 전체 조회하는 API 입니다.")
    @GetMapping("/posts")
    public ResponseEntity<?> getAllPost(Pageable pageable){
        log.info("[POST]: 모든 게시물 조회 요청");
        return postService.getAllPost(pageable);
    }

    @Operation(summary = "게시판 이메일 조회", description = "작성자 이메일을 통해 특정 게시물들을 검색하는 API 입니다.")
    @GetMapping("/posts/search")
    public ResponseEntity<?> getSearchPost(@RequestParam(name = "author_email") String searchEmail,Pageable pageable){
        log.info("[POST]: 게시물 검색 요청");
        return postService.getSearchPost(searchEmail,pageable);
    }

    @Operation(summary = "게시판 작성", description = "게시물을 " +
            "새롭게 만들 수 있는 API 입니다.")
   // @PostMapping("/v1/posts")
    @PostMapping("/posts")
    public ResponseEntity<?> postWrite(@RequestBody PostDto postDto
           // ,@AuthenticationPrincipal CustomUserDetails customUserDetails
    ){
        log.info("[POST]: 게시물 작성 요청");
        postService.postWrite(postDto,1);

        return ResponseEntity.ok(new MsgResponseDto("게시물이 성공적으로 작성되었습니다.", HttpStatus.OK.value()));
    }

    @Operation(summary = "게시판 상세조회", description = "게시물을 상세 조회할 수 있는 API 입니다.")
    @GetMapping("/detailPost")
    public ResponseEntity<?> getDetailPost(@RequestParam(name="post_id") Long postId){
        log.info("[Get]: 게시물 상세조회 요청");
        return postService.getDetailPost(postId);
    }

    @Operation(summary = "게시판 수정", description = "게시물을 새롭게 수정할 수 있는 API 입니다.")
    @PutMapping("/posts")
    public ResponseEntity<?> PostUpdate(@RequestBody PostDto postDto){
        log.info("[Get]: 게시물 수정 요청");
        postService.postUpdate(postDto,1);

        return ResponseEntity.ok(new MsgResponseDto("게시물이 수정되었습니다.", HttpStatus.OK.value()));
    }

    @Operation(summary = "게시판 삭제", description = "게시물을 삭제할 수 있는 API 입니다.")
    @DeleteMapping("/posts")
    public ResponseEntity<?> PostDelete(@RequestParam Long postId){
        log.info("[Get]: 게시물 삭제 요청");
        postService.postDelete(postId,1);

        return ResponseEntity.ok(new MsgResponseDto("게시물이 삭제되었습니다.", HttpStatus.OK.value()));
    }
}
