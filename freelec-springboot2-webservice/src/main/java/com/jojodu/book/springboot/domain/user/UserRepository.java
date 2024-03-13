package com.jojodu.book.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // User의 CRUD 담당

    Optional<User> findByEmail(String email); // 소셜 로그인
}
