package com.example.cache.service.impl;

import com.example.cache.domain.User;
import com.example.cache.service.UserService;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/8/9 下午2:55
 * @see jdk 1.7
 **/
@Service
public class UserServiceImpl implements UserService {

    @Cacheable(value = "user", key = "#uid")
    @Override
    public User getUserById(Long uid) {
        return null;
    }

    @CachePut(value = "user", key = "#user.id")
    @Override
    public User save(User user) {
        return user;
    }
}
