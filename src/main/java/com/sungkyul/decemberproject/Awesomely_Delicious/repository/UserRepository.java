package com.sungkyul.decemberproject.Awesomely_Delicious.repository;

import com.sungkyul.decemberproject.Awesomely_Delicious.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);

    User findById(Long id);

//    User findByName(String name);
//    List<User> findAll();
}

