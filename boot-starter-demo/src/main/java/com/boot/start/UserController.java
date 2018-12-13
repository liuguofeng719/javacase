package com.boot.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/10/6 下午1:16
 * @see jdk 1.7
 **/
@Controller
@RequestMapping("/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/getUserList")
    @ResponseBody
    public List<User> getUserList() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User us = new User();
            us.setAge(i + 1);
            us.setName("us" + i);
            us.setAddress("us_address" + i);
            us.setUid(Long.parseLong("" + i));
            UserExt userExt = new UserExt();
            userExt.setHeadImg("/usr/local/img/" + i + ".jpg");
            userExt.setIdNO("000000" + i);
            us.setUserExt(userExt);
            List<Coupon> coupons = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Coupon coupon = new Coupon();
                coupon.setCid(Long.parseLong(j + ""));
                coupon.setCname("优惠券名字" + j);
                coupon.setDesc("折扣券，满100使用");
                coupon.setMoney(100000L);
                coupon.setStatus(1);
                coupon.setType(1);
                coupons.add(coupon);
            }
            us.setCouponList(coupons);
            list.add(us);
        }

        return list;
    }

    @RequestMapping(value = "/getUserLists")
    @ResponseBody
    public BaseRespone<List<User>> getUserLists() {
        BaseRespone<List<User>> respone = new BaseRespone<>();
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User us = new User();
            us.setAge(i + 1);
            us.setName("us" + i);
            us.setAddress("us_address" + i);
            us.setUid(Long.parseLong("" + i));
            UserExt userExt = new UserExt();
            userExt.setHeadImg("/usr/local/img/" + i + ".jpg");
            userExt.setIdNO("000000" + i);
            us.setUserExt(userExt);
            List<Coupon> coupons = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Coupon coupon = new Coupon();
                coupon.setCid(Long.parseLong(j + ""));
                coupon.setCname("优惠券名字" + j);
                coupon.setDesc("折扣券，满100使用");
                coupon.setMoney(100000L);
                coupon.setStatus(1);
                coupon.setType(1);
                coupons.add(coupon);
            }
            us.setCouponList(coupons);
            list.add(us);
        }
        respone.setData(list);
        respone.setMsg("success");
        respone.setCode("200");
        return respone;
    }

    @RequestMapping(value = "/getUserById", method = RequestMethod.POST)
    @ResponseBody
    public User getUserById(@RequestBody UserReq req) {
        logger.info("getUserById={}", req.getUid());
        User us = new User();
        us.setAge(20);
        us.setName("us20");
        us.setAddress("us_address");
        us.setUid(Long.parseLong("1"));
        return us;
    }
}
