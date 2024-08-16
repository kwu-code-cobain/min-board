package com.cake.board.repository;

import com.cake.board.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByUserid(String userid);
    Optional<User> findByUserid(String userid);
    Optional<User> findByUsername(String username);
}
