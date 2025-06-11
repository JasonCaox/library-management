package cn.tcu.librarymanagement.service.impl;

import cn.tcu.librarymanagement.entity.User;
import cn.tcu.librarymanagement.mapper.UserMapper;
import cn.tcu.librarymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(User user) {
        userMapper.addUser(user);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}

