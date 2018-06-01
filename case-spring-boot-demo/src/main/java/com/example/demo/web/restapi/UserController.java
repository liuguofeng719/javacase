package com.example.demo.web.restapi;

import com.example.demo.domain.UserVo;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/5/22 上午10:28
 * @see jdk 1.7
 **/
@RestController
@RequestMapping("/users")
public class UserController {

    private ConcurrentHashMap<String, UserVo> userVos = new ConcurrentHashMap<>();

    @ApiOperation(value = "用户列表", notes = "")
    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public List<UserVo> getListUsers() {
        return new ArrayList<>(userVos.values());
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "", value = "用户详细实体user", required = true,paramType = "query")
    @RequestMapping(value = "/", method = {RequestMethod.POST})
    public String postUser(@ModelAttribute UserVo userVo) {
        userVos.put(userVo.getUid(), userVo);
        return "success";
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "userVo", value = "用户实体json", required = true, paramType = "body", dataType = "UserVo")
    @RequestMapping(value = "/json", method = {RequestMethod.POST})
    public String postUserJson(@RequestBody UserVo userVo) {
        userVos.put(userVo.getUid(), userVo);
        return "success";
    }

    @ApiOperation(value = "获取用户详情", notes = "通过url的用户ID获取用户详情")
    @ApiImplicitParam(name = "id", value = "用户的ID", required = true, paramType = "path", dataType = "String")
    @ApiResponse(code = 200, message = "获取用户成功")
    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public UserVo getUser(@PathVariable("id") String id) {
        return userVos.get(id);
    }

    @ApiOperation(value = "更新用户信息", notes = "通过用户url的用户id更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户的id", required = true, paramType = "path", dataType = "String"),
            @ApiImplicitParam(name = "userVo", value = "用户的实体", required = true, paramType = "path", dataType = "UserVo")
    })
    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT})
    public String putUser(@PathVariable String id, @ModelAttribute UserVo userVo) {
        UserVo vo = new UserVo();
        vo.setUserName(userVo.getUserName());
        vo.setAge(userVo.getAge());
        userVos.put(id, vo);
        return "success";
    }

    @ApiOperation(value = "删除用户", notes = "通过url的用户Id删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataType = "String")
    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE})
    public String deleteUser(@PathVariable String id) {
        userVos.remove(id);
        return "success";
    }
}
