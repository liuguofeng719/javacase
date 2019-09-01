package com.springboot2x.service.impl;

import com.springboot2x.dao.UserServiceDao;
import com.springboot2x.domain.User;
import com.springboot2x.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/8 9:24 PM
 * @see jdk 1.7
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserServiceDao userServiceDao;

    @Override
    public User getUserById(Long id) {
        return userServiceDao.getUserById(id);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,
            propagation = Propagation.NESTED)
    @Override
    public Long saveUser(User user) {
        final Long saveUser = userServiceDao.saveUser(user);
//        int i = 1 / 0;
        return saveUser;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,
            propagation = Propagation.NESTED)
    @Override
    public int delete(Long id) {
        return userServiceDao.delete(id);
    }

    @Override
    public void modify(User user) {

    }
}
