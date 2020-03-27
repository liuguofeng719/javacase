package com.boot.start;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-*.xml"})
@SpringBootTest
public class BootStarterDemoApplicationTests {

    @Resource
    IUserApi userApi;

    @Test
    public void contextLoads() {
        final List<User> userList = userApi.getUserList();
        System.out.println(userList);
    }
    @Test
    public void getUserLists() {
        final BaseRespone<List<User>> userLists = userApi.getUserLists();
        System.out.println(userLists.toString());
    }

    @Test
    public void testGetUserById() {
        UserReq userReq = new UserReq();
        userReq.setUid(1L);
        final User user = userApi.getUserById(userReq);
        System.out.println(user);
    }

}
