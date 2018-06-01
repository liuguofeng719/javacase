package com.demo.springboot.validator;

import com.google.common.collect.Maps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc
 * @createtime 2018/4/25 下午10:47
 * @see jdk 1.7
 **/
public class ValidatorProvider {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    //构建Validator 对象
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public <T> void validate(T vo) {
        //校验对象并且返回，违反约定的错误字段
        final Set<ConstraintViolation<T>> violations = validator.validate(vo);
        ValidatorResult validatorResult = new ValidatorResult();
        Map<String, String> map = Maps.newHashMap();
        for (final ConstraintViolation<T> violation : violations) {
            logger.error("msg={},getRootBean={},getPropertyPath={},getInvalidValue={}",
                    violation.getMessage(), violation.getRootBean(), violation.getPropertyPath(), violation.getInvalidValue());
        }
    }

    public static void main(String[] args) {
        ValidatorProvider provider = new ValidatorProvider();
        UserVo userVo = new UserVo();
        userVo.setName("1");
        userVo.setPwd("1232");
        userVo.setAge(3);
        provider.validate(userVo);
    }
}
