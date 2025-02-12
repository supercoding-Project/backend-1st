package com.github.firstproject.post.service;

import com.github.firstproject.auth.entity.UserEntity;
import com.github.firstproject.auth.repository.UserRepository;
import com.github.firstproject.global.exception.AppException;
import com.github.firstproject.global.exception.ErrorCode;
import com.github.firstproject.post.dto.PostDto;
import com.github.firstproject.post.entity.PostEntity;
import com.github.firstproject.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;


    @Transactional
    public void postWrite(PostDto postDto, int userId) {
        UserEntity userEntity = findUserById(userId);

        PostEntity postEntity = PostEntity.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .userEntity(userEntity)
                .createdAt(LocalDateTime.now())
                .build();

        postRepository.save(postEntity);
    }

    @Transactional
    public void postUpdate(PostDto postDto, int userId) {
        PostEntity postEntity = findPostById((postDto.getPostId()));
        UserEntity userEntity = findUserById(userId);
        // 수정하는 사람이 작성자와 일치한지 확인 필요
        if(userEntity.getUserId().equals(postEntity.getUserEntity().getUserId())) {
            throw new AppException(ErrorCode.NOT_EQUAL_POST_USER,ErrorCode.NOT_EQUAL_POST_USER.getMessage());
        }
        postEntity.setPostEntity(postDto);
    }

    @Transactional
    public void postDelete(Long postId, int userId) {
        PostEntity postEntity = findPostById((postId));
        UserEntity userEntity = findUserById(userId);
        // 삭제하는 사람이 작성자와 일치한지 확인 필요
        if(userEntity.getUserId().equals(postEntity.getUserEntity().getUserId())) {
           throw new AppException(ErrorCode.NOT_EQUAL_POST_USER,ErrorCode.NOT_EQUAL_POST_USER.getMessage());
        }
        postRepository.delete(postEntity);
    }

    @Transactional
    public ResponseEntity<?> getDetailPost(Long postId) {
        PostEntity postEntity = findPostById((postId));
        UserEntity userEntity = findUserById(postEntity.getUserEntity().getUserId());

       PostDto postDto = new PostDto(
                postEntity.getPostId(),
                postEntity.getTitle(),
                postEntity.getContent(),
                userEntity.getUsername(),
                postEntity.getCreatedAt()
        );

        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    ////////////////////////////////////////////////////////////////
    /// 예외처리 method
    private PostEntity findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.CHECK_POST_ID,ErrorCode.CHECK_POST_ID.getMessage()));
    }

    private UserEntity findUserById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new AppException(ErrorCode.CHECK_USER_ID,ErrorCode.CHECK_USER_ID.getMessage()));

    }
    ////////////////////////////////////////////////////////////////


    @Transactional
    public ResponseEntity<?> getAllPost(Pageable pageable){
        Page<PostEntity> postEntityPage = postRepository.findAll(pageable);

        List<PostDto> postDtoList = new ArrayList<>();
        for (PostEntity postEntity : postEntityPage) {
            PostDto postDto = new PostDto(
                    postEntity.getPostId(),
                    postEntity.getTitle(),
                    postEntity.getContent(),
                    postEntity.getUserEntity().getUsername(),
                    postEntity.getCreatedAt()
            );
            postDtoList.add(postDto);
        }

        return ResponseEntity.ok(postDtoList);
    }

    @Transactional
    public ResponseEntity<?> getSearchPost(String searchEmail,Pageable pageable){
        Page<PostEntity> postEntityPage = postRepository.pagePostList(searchEmail,pageable);

        List<PostDto> postDtoList = new ArrayList<>();
        for (PostEntity postEntity : postEntityPage) {
            PostDto postDto = new PostDto(
                    postEntity.getPostId(),
                    postEntity.getTitle(),
                    postEntity.getContent(),
                    postEntity.getUserEntity().getUsername(),
                    postEntity.getCreatedAt()
            );
            postDtoList.add(postDto);
        }

        return ResponseEntity.ok(postDtoList);
    }
}