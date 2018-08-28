package com.example.cache.service;

import com.example.cache.domain.User;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/8/9 下午2:54
 * @see jdk 1.7
 **/
public interface UserService {

    User getUserById(Long uid);

    User save(User user);

}
