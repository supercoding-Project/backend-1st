package com.github.firstproject.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 에러코드 정리
    USER_EMAIL_DUPLICATED(HttpStatus.CONFLICT, "이미 가입된 이메일입니다."),
    USER_EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "이메일을 찾을 수 없습니다."),
    USERNAME_DUPLICATED(HttpStatus.CONFLICT, "이미 존재하는 닉네임입니다."),
    BINDING_RESULT_ERROR(HttpStatus.BAD_REQUEST, "데이터 유효성에 문제가 있습니다."),
    CHECK_EMAIL_OR_PASSWORD(HttpStatus.NOT_FOUND, "이메일 또는 비밀번호가 올바르지 않습니다."),
    NOT_EQUAL_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 올바르지 않습니다"),


    // PostsErrorCode
    NOT_EQUAL_POST_USER(HttpStatus.BAD_REQUEST, "게시물 수정/삭제 권한이 없습니다."),
    CHECK_USER_ID(HttpStatus.NOT_FOUND, "작성자 정보가 유효하지않습니다."),
    CHECK_POST_ID(HttpStatus.NOT_FOUND, "게시물이 유효하지않습니다."),
    NOT_ACCEPT(HttpStatus.NOT_ACCEPTABLE, "해당 페이지에 오류가 발생했습니다."),
      
    // comment error code
    NOT_ACCEPT_SAVE(HttpStatus.NOT_ACCEPTABLE,"저장이 완료 되지 않았습니다."),
    NOT_FOUND_COMMENT(HttpStatus.NOT_FOUND,"해당 댓글을 찾을 수 없습니다."),
    NOT_ACCEPT_CHANGE(HttpStatus.NOT_ACCEPTABLE,"변경이 완료 되지 않았습니다."),
    NOT_EQUAL_USER(HttpStatus.BAD_REQUEST,"댓글 작성자가 아닙니다."),
    NOT_ACCEPT_DELETE(HttpStatus.NOT_ACCEPTABLE,"댓글 삭제에 실패했습니다."),


    ;

    private final HttpStatus httpStatus;
    private final String message;
}
