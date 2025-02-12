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
    private String author; //username : 프로트에서 필요한 정보는 작성자 이름이니 username 전달
    private Integer postId;
    private LocalDateTime createdAt;
<<<<<<< HEAD
}
=======
}
>>>>>>> 81a22f91a9f3c714c3e5f6d3cc16e82dc7080521
