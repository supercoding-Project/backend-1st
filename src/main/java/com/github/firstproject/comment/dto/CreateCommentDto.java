package com.github.firstproject.comment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCommentDto {
    private String content;
    private String author;
    private Integer postId;
}