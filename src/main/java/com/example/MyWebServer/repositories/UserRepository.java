package com.example.MyWebServer.repositories;

import com.example.MyWebServer.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByName(String name);
}
