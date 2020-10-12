package com.xyy.mail.mail.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.xyy.mail.mail.entity.Student;
import com.xyy.mail.mail.mapper.LoginMapper;
import com.xyy.mail.mail.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yayu
 * @title: LoginServiceImpl
 * @description: TODO 有手就行.
 * @date 2020/10/10 15:12
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public Map checkLogin(Student student) {
        Student su = loginMapper.findStudentByName(student.getName());
        if (ObjectUtil.isNotNull(su)) {
            if (encoder.matches(student.getPassword(), su.getPassword())) {
                return new HashMap() {{
                    put("msg", "密码匹配正确,登录成功!");
                    put("code", "200");
                }};
            }
        }
        return new HashMap() {{
            put("msg", "密码匹配错误,登录失败!");
            put("code", "403");
        }};
    }
}
