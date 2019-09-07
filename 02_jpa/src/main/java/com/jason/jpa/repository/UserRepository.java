package com.jason.jpa.repository;

import com.jason.jpa.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByEmail(String email);

    List<User> findByName(String name);

    void deleteByEmail(String email);
}
