package com.github.firstproject.likes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LikesDto {
    private String username;
    private Integer commentId;
}
