package com.github.firstproject.comment.service;

import com.github.firstproject.auth.entity.UserEntity;
import com.github.firstproject.comment.dto.CommentDto;
import com.github.firstproject.comment.dto.CreateCommentDto;
<<<<<<< HEAD
import com.github.firstproject.comment.entity.Comment;
import com.github.firstproject.comment.repository.CommentRepository;
import com.github.firstproject.global.exception.AppException;
=======
import com.github.firstproject.comment.dto.UpdateCommentDto;
import com.github.firstproject.comment.entity.Comment;
import com.github.firstproject.comment.repository.CommentRepository;
import com.github.firstproject.global.exception.AppException;
import com.github.firstproject.global.exception.ErrorCode;
>>>>>>> 81a22f91a9f3c714c3e5f6d3cc16e82dc7080521
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
<<<<<<< HEAD
=======
import java.util.Objects;
>>>>>>> 81a22f91a9f3c714c3e5f6d3cc16e82dc7080521
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    public List<CommentDto> getComments(Integer postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment ->
                CommentDto.builder()
                        .id(comment.getComment_id())
                        .content(comment.getContent())
                        .author(comment.getUserEntity().getUsername())
                        .postId(comment.getPostId())
                        .createdAt(comment.getCreatedAt())
<<<<<<< HEAD
                        .build()).collect(Collectors.toList());
=======
                                .build()).collect(Collectors.toList());
>>>>>>> 81a22f91a9f3c714c3e5f6d3cc16e82dc7080521

    }
    @Transactional
    public Boolean addComment(CreateCommentDto createCommentDto, UserEntity userEntity) {
        //save
        Boolean isSuccess = false;
        try {
            commentRepository.save(
                    Comment.builder()
                            .content(createCommentDto.getContent())
                            .userEntity(userEntity)
                            .postId(createCommentDto.getPostId())
                            .createdAt(LocalDateTime.now()).build()
            );
<<<<<<< HEAD
        } catch (Exception e) {
            isSuccess = false;
            //throw new AppException()
        }
        return isSuccess;
    }
}
=======
            isSuccess = true;
        } catch (Exception e) {
            isSuccess = false;
            throw new AppException(ErrorCode.NOT_ACCEPT_SAVE,ErrorCode.NOT_ACCEPT_SAVE.getMessage());
        }
        return isSuccess;
    }
    @Transactional
    public Boolean updateComment(Integer commentId, UpdateCommentDto updateCommentDto, UserEntity user) {
        // 작성된 comment 가져오기
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_COMMENT,ErrorCode.NOT_FOUND_COMMENT.getMessage()));
        boolean isSuccess = false;
        // 로그인한 user와 comment 작성자가 동일한지 확인하기
        if (!comment.getUserEntity().getUserId().equals(user.getUserId())) {
            throw new AppException(ErrorCode.NOT_EQUAL_USER,ErrorCode.NOT_EQUAL_USER.getMessage());
        }
        else {
            try {
                comment.setContent(updateCommentDto.getContent());
                comment.setCreatedAt(LocalDateTime.now());
                isSuccess = true;
            } catch (Exception e) {
                throw new AppException(ErrorCode.NOT_ACCEPT_CHANGE, ErrorCode.NOT_ACCEPT_CHANGE.getMessage());
            }
        }
        return isSuccess;
    }

    public Boolean deleteComment(Integer commentId, UserEntity user) {
        // 작성된 comment 가져오기
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_COMMENT,ErrorCode.NOT_FOUND_COMMENT.getMessage()));
        boolean isSuccess = false;
        // 로그인한 user와 comment 작성자가 동일한지 확인하기
        if (!comment.getUserEntity().getUserId().equals(user.getUserId())) {
            throw new AppException(ErrorCode.NOT_EQUAL_USER,ErrorCode.NOT_EQUAL_USER.getMessage());
        }
        else {
            try{
                commentRepository.delete(comment);
                isSuccess = true;
            } catch (Exception e) {
                throw new AppException(ErrorCode.NOT_ACCEPT_DELETE,ErrorCode.NOT_ACCEPT_DELETE.getMessage());
            }
        }
        return isSuccess;
    }
}
>>>>>>> 81a22f91a9f3c714c3e5f6d3cc16e82dc7080521
