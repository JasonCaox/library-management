package cn.tcu.librarymanagement.service;

import cn.tcu.librarymanagement.entity.User;

public interface UserService {
    void register(User user);
    User findByUsername(String username);
}

