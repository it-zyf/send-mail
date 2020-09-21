package com.xyy.mail.mail.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.xyy.mail.mail.entity.MailConstants;
import com.xyy.mail.mail.entity.MailSendLog;
import com.xyy.mail.mail.entity.Student;
import com.xyy.mail.mail.mapper.AddUserMapper;
import com.xyy.mail.mail.mapper.MailSendLogMapper;
import com.xyy.mail.mail.service.AddUserService;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    @Autowired
    private MailSendLogMapper mailSendLogMapper;


    @Override
    public Map addUser(Student student) {
        try {
            student.setId(String.valueOf(snowflake.nextId()));
            adduserMapper.addStudent(student);
            MailSendLog mailSendLog = new MailSendLog();
            mailSendLog.setMsgId(IdUtil.randomUUID());
            mailSendLog.setEmpId(student.getId());
            mailSendLog.setCreateTime(new Date());
            mailSendLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
            mailSendLog.setRouteKey(MailConstants.MAIL_ROUTING_KEY_NAME);
            mailSendLog.setStatus(0);
            mailSendLog.setTryTime(new Date(System.currentTimeMillis() + 1000 * 60 * 1));
            mailSendLogMapper.insertMailsendLog(mailSendLog);
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME, student, new CorrelationData(mailSendLog.getMsgId()));
            return new HashMap(){{put("mes","插入成功");}};
        } catch (AmqpException e) {
            e.printStackTrace();
        }
        return new HashMap();
    }
}
