package com.github.firstproject.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    @JsonProperty("post_id")
    private Long postId;
    private String title;
    private String content;
    private String author;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}