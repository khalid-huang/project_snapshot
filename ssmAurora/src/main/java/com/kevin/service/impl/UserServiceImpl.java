package com.kevin.service.impl;

import com.kevin.dao.UserDao;
import com.kevin.model.User;
import com.kevin.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    public User selectUser(long userId) {
        return this.userDao.selectUser(userId);
    }

}
