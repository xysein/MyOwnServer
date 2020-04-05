package com.example.MyWebServer.repositories;

import com.example.MyWebServer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String username);
}
