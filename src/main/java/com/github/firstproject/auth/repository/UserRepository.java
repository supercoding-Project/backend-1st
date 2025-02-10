package com.github.firstproject.auth.repository;

import com.github.firstproject.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    // 이메일로 유저 찾기
    Optional<UserEntity> findByEmail(String email);
    // 유저네임으로 유저 찾기
    Optional<UserEntity> findByUsername(String username);
}
