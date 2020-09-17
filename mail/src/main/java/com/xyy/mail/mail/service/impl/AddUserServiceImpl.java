package com.xyy.mail.mail.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.xyy.mail.mail.entity.Student;
import com.xyy.mail.mail.mapper.AddUserMapper;
import com.xyy.mail.mail.service.AddUserService;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yayu
 * @title: AddUserServiceImpl
 * @description: TODO
 * @date 2020/9/1716:33
 */
@Service
public class AddUserServiceImpl implements AddUserService {
    @Autowired
    private AddUserMapper adduserMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Snowflake snowflake;

    @Override
    public Map addUser(Student student) {
        try {
            student.setId(String.valueOf(snowflake.nextId()));
            adduserMapper.addStudent(student);
            rabbitTemplate.convertAndSend("xyy",student);
            return new HashMap(){{put("mes","插入成功");}};
        } catch (AmqpException e) {
            e.printStackTrace();
        }
        return new HashMap();
    }
}
