package com.springboot2x.dao.impl;

import com.springboot2x.dao.UserServiceDao;
import com.springboot2x.domain.User;
import com.springboot2x.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/13 10:01 PM
 * @see jdk 1.7
 **/
@Repository
public class UserServiceDaoImpl implements UserServiceDao {

    @Autowired
    private UserMapper mapper;

    @Override
    public User getUserById(Long id) {
        return mapper.getUserById(id);
    }

    @Override
    public Long saveUser(User user) {
        return mapper.saveUser(user);
    }

    @Override
    public int delete(Long id) {
        return mapper.delete(id);
    }
}
