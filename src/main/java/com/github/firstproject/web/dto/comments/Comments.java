package com.github.firstproject.web.dto.comments;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comments {
    private String content;
    private String author;
    private Integer postId;
    private LocalDateTime createdAt;
}
