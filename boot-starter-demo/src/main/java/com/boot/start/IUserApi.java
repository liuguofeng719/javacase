package com.boot.start;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/10/6 下午1:09
 * @see jdk 1.7
 **/
public interface IUserApi {

    List<User> getUserList();

    User getUserById(UserReq req);

    BaseRespone<List<User>> getUserLists();
}
