package com.xyy.mail.mail.controller;

import com.xyy.mail.mail.entity.Student;
import com.xyy.mail.mail.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author yayu
 * @title: LoginController
 * @description: TODO
 * @date 2020/10/10 14:50
 */
@RequestMapping("/user")
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public Map checkLogin(@RequestBody Student student) {
        return loginService.checkLogin(student);
    }
}
