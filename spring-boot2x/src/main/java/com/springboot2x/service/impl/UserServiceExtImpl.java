package com.springboot2x.service.impl;

import com.springboot2x.domain.User;
import com.springboot2x.enumeration.SexEnum;
import com.springboot2x.service.UserService;
import com.springboot2x.service.UserServiceExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/13 10:07 PM
 * @see jdk 1.7
 **/
@Service
public class UserServiceExtImpl implements UserServiceExt {

    @Autowired
    private UserService userService;

    @Transactional(isolation = Isolation.REPEATABLE_READ,
            propagation = Propagation.NESTED)
    @Override
    public void saveOrDelete() throws Exception {
        userService.delete(26L);
        User user = new User();
        user.setUserName("1323");
        user.setSexEnum(SexEnum.FEMALE);
        user.setNote("555");
        userService.saveUser(user);
        int i = 1/0;
    }
}
