package com.kevin.dao;

import com.kevin.model.User;


public interface UserDao {
    User selectUser(long id);
}
