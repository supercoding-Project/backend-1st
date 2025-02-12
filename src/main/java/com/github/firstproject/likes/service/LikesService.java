package com.github.firstproject.likes.service;

import com.github.firstproject.auth.entity.UserEntity;
import com.github.firstproject.auth.repository.UserRepository;
import com.github.firstproject.comment.entity.Comment;
import com.github.firstproject.comment.repository.CommentRepository;
import com.github.firstproject.likes.entity.LikesEntity;
import com.github.firstproject.likes.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class LikesService {

    public final UserRepository userRepository;
    public final LikesRepository likesRepository;
    public final CommentRepository commentRepository;

    @Transactional
    public boolean addLike(UserEntity userEntity, Integer commentId) throws Exception {

        // 1. 댓글 조회
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("댓글이 존재하지 않습니다"));

        // 2. 해당 회원이 이미 해당 댓글에 좋아요를 눌렀는지 확인
        LikesEntity likes = likesRepository.findByUserEntityAndComment(userEntity,comment)
                .orElseGet(() -> {
            LikesEntity likeEntity = new LikesEntity();
            likeEntity.setUserEntity(userEntity);
            likeEntity.setComment(comment);
            return likesRepository.save(likeEntity);
        });

        // 3. 좋아요 추가
        LikesEntity likeEntity = new LikesEntity();
        likeEntity.setUserEntity(userEntity);
        likeEntity.setComment(comment);
        likesRepository.save(likeEntity);

        return true;
    }

    @Transactional
    public boolean deleteLike(UserEntity userEntity, Integer commentId) throws Exception {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("댓글이 존재하지 않습니다"));

        // 1. 해당 회원과 댓글에 대한 좋아요 엔티티 조회
        LikesEntity likesEntity = likesRepository.findByUserEntityAndComment(userEntity, comment)
                .orElseThrow(() -> new NotFoundException("해당 댓글에 좋아요가 없습니다"));

        // 2. 좋아요 엔티티 삭제
        likesRepository.delete(likesEntity);
        return true;
    }
}