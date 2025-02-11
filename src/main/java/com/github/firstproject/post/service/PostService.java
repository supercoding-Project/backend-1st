package com.github.firstproject.post.service;

import com.github.firstproject.auth.entity.UserEntity;
import com.github.firstproject.auth.repository.UserRepository;
import com.github.firstproject.global.exception.AppException;
import com.github.firstproject.global.exception.ErrorCode;
import com.github.firstproject.post.dto.PostDto;
import com.github.firstproject.post.entity.PostEntity;
import com.github.firstproject.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;


    @Transactional
    public void postWrite(PostDto postDto) {
        UserEntity userEntity = userRepository.findById(postDto.getAuthor())
                .orElseThrow(()-> new AppException(ErrorCode.BINDING_RESULT_ERROR,"아이디가 유효하지않습니다."));

        PostEntity postEntity = PostEntity.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .userEntity(userEntity)
                .createdAt(LocalDateTime.now())
                .build();

        postRepository.save(postEntity);
    }

    @Transactional
    public ResponseEntity<?> getDetailPost(Long postId) {
        Optional<PostEntity> postEntity = postRepository.findById(postId);

        if(postEntity.isPresent()) {
           // throw new AppException();
            //errorcode 추가필요
        }

//        UserEntity userEntity = userRepository.findById(postEntity.get().getUserEntity().getUserId())
//                .orElseThrow(()-> new AppException(ErrorCode.BINDING_RESULT_ERROR,"아이디가 유효하지않습니다."));

        PostDto postDto = new PostDto(
                postEntity.get().getPostId(),
                postEntity.get().getTitle(),
                postEntity.get().getContent(),
                postEntity.get().getUserEntity().getUserId(),
                postEntity.get().getUserEntity().getUsername(),
                postEntity.get().getCreatedAt()
        );

        return new ResponseEntity<>(postDto, HttpStatus.OK);

    }

}