package com.springboot2x.dao;

import com.springboot2x.domain.User;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/13 10:00 PM
 * @see jdk 1.7
 **/
public interface UserServiceDao {
    User getUserById(Long id);
    Long saveUser(User user);
    int delete(Long id);
}
