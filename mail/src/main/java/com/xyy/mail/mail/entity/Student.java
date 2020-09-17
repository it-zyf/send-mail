package com.xyy.mail.mail.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yayu
 * @title: Student
 * @description: TODO
 * @date 2020/9/1716:30
 */
@Data
public class Student implements Serializable{
    private String id;
    private String name;
    private String password;
    private String image;
}
