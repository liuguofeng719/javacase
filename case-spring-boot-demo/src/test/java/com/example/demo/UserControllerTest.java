package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.domain.UserVo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/5/22 上午11:04
 * @see jdk 1.7
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {


    private MockMvc mockMvc;

    @Resource
    WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void userInfo() throws Exception {
        RequestBuilder request;

        // 1、get查一下user列表，应该为空
        request = get("/users/");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

        //2、增加用户
        request = post("/users/")
                .param("userName","Mr张")
                .param("age","20")
                .param("address","武侯区")
                .param("pwd","123")
                .param("uid","99999");
        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));

        // 通过json 方式
        UserVo userVo = new UserVo();
        userVo.setAge(20);
        userVo.setPwd("2323");
        userVo.setUid("2");
        userVo.setAddress("高新区");
        userVo.setUserName("王导");

        request = post("/users/json").content(JSON.toJSONString(userVo)).contentType(MediaType.APPLICATION_JSON_UTF8);

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));

        //3，获取id获取用户
        request= get("/users/2");
        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());


        // 4 通过id 更新用户
        request = put("/users/2")
                .param("age","30")
                .param("userName","小王");

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));

        request= get("/users/2");
        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        // 5 删除用户
        request = delete("/users/2");
        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));

    }
}
