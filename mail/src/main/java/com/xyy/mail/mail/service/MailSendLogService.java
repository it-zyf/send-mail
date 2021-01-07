package com.xyy.mail.mail.service;

import com.xyy.mail.mail.entity.Infomation;
import com.xyy.mail.mail.entity.MailSendLog;
import com.xyy.mail.mail.entity.Student;

import java.util.Date;
import java.util.List;

/**
 * @author yayu
 * @title: MailSendLogService
 * @description: TODO
 * @date 2020/9/1810:42
 */
public interface MailSendLogService {
     void insertMailsendLog(MailSendLog mailSendLog);
     void updateMailsendLog(String id,int status);

     List<MailSendLog> getStudentByStatusTryTime();

     void updateCount(Date date, String msgId);

     Student getStudentByMsgId(String empId);

     void insertInfomation(Infomation infomation);

    Long aInfomation();
}
