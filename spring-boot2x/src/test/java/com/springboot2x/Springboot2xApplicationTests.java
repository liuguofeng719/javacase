package com.springboot2x;

import com.springboot2x.domain.User;
import com.springboot2x.enumeration.SexEnum;
import com.springboot2x.service.UserService;
import com.springboot2x.service.UserServiceExt;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Springboot2xApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceExt userServiceExt;

    @Test
    public void contextLoads() {
        final User user = restTemplate.getForObject("/user/{id}", User.class, 1L);
        Assert.assertNotNull(user);
    }

    @Test
    public void saveUser(){
        User user = new User();
        user.setNote("44");
        user.setSexEnum(SexEnum.MALE);
        user.setUserName("wangwu");
        userService.saveUser(user);
        System.out.println("primaryKey = " + user.getId());
    }

    @Test
    public void testUser() throws Exception {
        userServiceExt.saveOrDelete();
    }
}