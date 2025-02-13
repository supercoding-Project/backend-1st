package com.github.firstproject.comment.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private Integer id;
    private String content;
    private String author; //username : 프론트에서 필요한 정보는 작성자 이름이니 username 전달
    private Integer postId;
    private LocalDateTime createdAt;
}
