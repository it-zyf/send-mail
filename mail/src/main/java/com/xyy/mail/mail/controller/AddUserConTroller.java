package com.xyy.mail.mail.controller;

import com.xyy.mail.mail.entity.Student;
import com.xyy.mail.mail.service.AddUserService;
import com.xyy.mail.mail.service.MailSendLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author yayu
 * @title: AddUserConTroller
 * @description: TODO
 * @date 2020/9/1716:28
 */
@RestController
@RequestMapping("/add")
public class AddUserConTroller {
    @Autowired
    private AddUserService addUserService;

    @Autowired
    private MailSendLogService  mailSendLogService;

    @RequestMapping("/user")
    public Map add(@RequestBody Student student) {
        return addUserService.addUser(student);
    }

    //测试添加数据主键回显
    @RequestMapping("/infomation")
    public Long addInfomation(){
        return mailSendLogService.aInfomation();
    }




}
