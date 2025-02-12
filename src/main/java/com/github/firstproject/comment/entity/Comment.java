package com.github.firstproject.comment.entity;

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

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
<<<<<<< HEAD
}
=======
}
>>>>>>> 81a22f91a9f3c714c3e5f6d3cc16e82dc7080521
