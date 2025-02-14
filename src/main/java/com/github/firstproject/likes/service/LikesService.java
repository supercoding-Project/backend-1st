package com.github.firstproject.likes.service;

import com.github.firstproject.auth.entity.UserEntity;
import com.github.firstproject.auth.repository.UserRepository;
import com.github.firstproject.comment.entity.Comment;
import com.github.firstproject.comment.repository.CommentRepository;
import com.github.firstproject.likes.entity.LikesEntity;
import com.github.firstproject.likes.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikesService {

    public final UserRepository userRepository;
    public final LikesRepository likesRepository;
    public final CommentRepository commentRepository;

    @Transactional
    public Boolean addLike(UserEntity userEntity, Integer commentId) throws NotFoundException {

        // 1. 댓글 조회
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("댓글이 존재하지 않습니다"));

//        // 2. 해당 회원이 이미 해당 댓글에 좋아요를 눌렀는지 확인
//        LikesEntity likesEntity = likesRepository.findByUserEntityAndComment(userEntity, comment)
//                .orElse(null);
//
//        if (likesEntity != null) {
//            return false;
//        }
//
//        // 3. 좋아요 추가
//        LikesEntity likeEntity = new LikesEntity();
//        likeEntity.setUserEntity(userEntity);
//        likeEntity.setComment(comment);
//        likesRepository.save(likeEntity);
//
//        return true;

        // 2. 해당 회원이 이미 해당 댓글에 좋아요를 눌렀는지 확인
        LikesEntity likes = likesRepository.findByUserEntityAndComment(userEntity,comment).orElse(null);
        // 해당 값이 없다면
        if (likes == null) {
            LikesEntity likeEntity = new LikesEntity();
            likeEntity.setUserEntity(userEntity);
            likeEntity.setComment(comment);
            likesRepository.save(likeEntity);
            return true;
        }
        else{
            return false;
        }

    }

    @Transactional
    public Boolean deleteLike(UserEntity userEntity, Integer commentId) throws NotFoundException {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("댓글이 존재하지 않습니다"));

        // 해당 유저와 댓글에 대한 좋아요 존재 여부 확인
         LikesEntity likesEntity = likesRepository.findByUserEntityAndComment(userEntity, comment)
                .orElse(null);

        if (likesEntity != null) {
            // 좋아요가 존재하면 삭제
            likesRepository.delete(likesEntity);
            return true;
        } else {
            // 좋아요가 존재하지 않으면 false 반환
            return false;
        }
    }
}