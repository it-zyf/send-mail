package com.xyy.mail.mail.service;

import com.xyy.mail.mail.entity.Student;

import java.util.Map;

/**
 * @author yayu
 * @title: LoginService
 * @description: TODO
 * @date 2020/10/10 14:52
 */
public interface LoginService {
    Map checkLogin(Student student);
}
