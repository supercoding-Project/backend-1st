package com.github.firstproject.likes.entity;

import com.github.firstproject.auth.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comment_id;

    @Column(nullable = false, length = 255)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "post_id", nullable = false)
    //private PostEntity postEntity;
    @Column(nullable = false)
    private Integer postId;

//    @Column
//    private Integer parentCommentId;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}