package com.jason.mybatismulti.mapper.test2;

import com.jason.mybatismulti.entity.User;

import java.util.List;

public interface User2Mapper {
    List<User> getAll();
    User getOne(Long id);
    int insert(User user);
    int update(User user);
    void delete(Long id);
}
