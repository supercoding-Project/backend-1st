package com.github.firstproject.global.config.auth.custom;

import com.github.firstproject.auth.entity.UserEntity;
import com.github.firstproject.auth.repository.UserRepository;
import com.github.firstproject.global.exception.AppException;
import com.github.firstproject.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Log4j2
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_EMAIL_NOT_FOUND, ErrorCode.USER_EMAIL_NOT_FOUND.getMessage()));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        return new CustomUserDetails(userEntity);
    }
}
