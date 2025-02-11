package com.github.firstproject.post.entity;

import com.github.firstproject.auth.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "post")
public class PostEntity {
    @Id
    @Column(name="post_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;  // 게시물 ID

    @Column(name="title",nullable = false)
    private String title;  // 제목

    @Column(name="content",nullable = false)
    private String content;  // 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;  // 작성자

    @Column(name="created_at",nullable = false)
    private LocalDateTime createdAt;  // 작성일
}
