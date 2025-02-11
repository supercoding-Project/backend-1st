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
    private String author; //username
    private Integer postId;
    private LocalDateTime createdAt;
}
