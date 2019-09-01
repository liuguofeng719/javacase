package com.springboot2x.service;

import com.springboot2x.domain.User;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/8 9:24 PM
 * @see jdk 1.7
 **/
public interface UserService {
    User getUserById(Long id);
    Long saveUser(User user);
    int delete(Long id);
    void modify(User user);
}
