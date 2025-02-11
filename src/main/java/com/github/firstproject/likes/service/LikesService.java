package com.github.firstproject.likes.service;

import com.github.firstproject.auth.entity.UserEntity;
import com.github.firstproject.auth.repository.UserRepository;
import com.github.firstproject.likes.entity.Comment;
import com.github.firstproject.likes.entity.LikesEntity;
import com.github.firstproject.likes.repository.CommentRepository;
import com.github.firstproject.likes.repository.LikesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.Optional;

@Service
public class LikesService {

    public final UserRepository userRepository;
    public final LikesRepository likesRepository;
    public final CommentRepository commentRepository;

    public LikesService(CommentRepository commentRepository, UserRepository userRepository, LikesRepository likeRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.likesRepository = likeRepository;
    }

    @Transactional
    public boolean addLike(String username, Integer postId) {
        // 1. 회원 조회
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("회원이 존재하지 않습니다"));

        // 2. 댓글 조회
        Comment comment = commentRepository.findByPostId(postId)
                .stream().findFirst()  // 첫 번째 댓글을 가져옴
                .orElseThrow(() -> new NotFoundException("댓글이 존재하지 않습니다"));

        // 3. 해당 회원이 이미 해당 댓글에 좋아요를 눌렀는지 확인
        Optional<LikesEntity> likesEntity = likesRepository.findByUserUsernameAndCommentPostId(username, postId);
        if (likesEntity.isPresent()) {
            // 이미 좋아요가 존재하면 추가하지 않음
            return false;
        }

        // 4. 좋아요 추가
        LikesEntity likeEntity = new LikesEntity();
        likeEntity.setUser(userEntity);
        likeEntity.setComment(comment);
        likesRepository.save(likeEntity);

        return true;
    }

    @Transactional
    public boolean removeLike(String username, Integer postId) {
        // 1. 해당 회원과 댓글에 대한 좋아요 엔티티 조회
        LikesEntity likeEntity = likesRepository.findByUserUsernameAndCommentPostId(username, postId)
                .orElseThrow(() -> new RuntimeException("해당 댓글에 좋아요가 없습니다"));

        // 2. 좋아요 엔티티 삭제
        likesRepository.delete(likeEntity);
        return true;
    }
}
