package org.example.blogapi.repository;

import org.example.blogapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Boolean existsUserByUsername(String username);

    Boolean existsUserByEmail(String email);
}
