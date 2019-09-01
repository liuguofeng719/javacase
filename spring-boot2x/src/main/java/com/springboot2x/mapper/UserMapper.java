package com.springboot2x.mapper;

import com.springboot2x.domain.User;
import org.springframework.stereotype.Repository;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/8 9:08 PM
 * @see jdk 1.7
 **/
@Repository
public interface UserMapper {
    User getUserById(Long id);
    Long saveUser(User user);
    int delete(Long id);
}
