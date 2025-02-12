package com.github.firstproject.comment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCommentDto {
    private String content;
}
