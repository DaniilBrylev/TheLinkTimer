package com.example.TheLinkTamer.repository1;

import com.example.TheLinkTamer.model1.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUuid(String uuid);
}
