package com.xyy.mail.mail.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.xyy.mail.mail.entity.Infomation;
import com.xyy.mail.mail.entity.MailSendLog;
import com.xyy.mail.mail.entity.Student;
import com.xyy.mail.mail.mapper.MailSendLogMapper;
import com.xyy.mail.mail.service.MailSendLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author yayu
 * @title: MailSendLogServiceImpl
 * @description: TODO
 * @date 2020/9/1810:45
 */
@Service
public class MailSendLogServiceImpl implements MailSendLogService {
    @Autowired
    private MailSendLogMapper mailSendLogMapper;

    @Autowired
    private Snowflake snowflake;

    @Override
    public void insertMailsendLog(MailSendLog mailSendLog) {
        mailSendLogMapper.insertMailsendLog(mailSendLog);
    }

    @Override
    public void updateMailsendLog(String id, int status) {
        mailSendLogMapper.updateMailsendLog(id,status);
    }

    @Override
    public List<MailSendLog> getStudentByStatusTryTime() {
        return mailSendLogMapper.getStudentByStatusTryTime();
    }

    @Override
    public void updateCount(Date date, String msgId) {
        mailSendLogMapper.updateCount(date,msgId);
    }

    @Override
    public Student getStudentByMsgId(String empId) {
        return mailSendLogMapper.getStudentByMsgId(empId);
    }

    @Override
    public void insertInfomation(Infomation infomation) {
        mailSendLogMapper.insertInfomation(infomation);
    }

    @Override
    public Long aInfomation() {
        Infomation build = Infomation.builder().name("张亚锋").age(24).sex("男").time(new Date()).id(String.valueOf(snowflake.nextId())).build();
        return mailSendLogMapper.ainsertInfomation(build);
    }


}
