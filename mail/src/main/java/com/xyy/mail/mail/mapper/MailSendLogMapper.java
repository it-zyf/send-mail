package com.xyy.mail.mail.mapper;

import com.xyy.mail.mail.entity.Infomation;
import com.xyy.mail.mail.entity.MailSendLog;
import com.xyy.mail.mail.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author yayu
 * @title: MailSendLogMapper
 * @description: TODO
 * @date 2020/9/1810:51
 */
@Repository
public interface MailSendLogMapper {
    void insertMailsendLog(MailSendLog mailSendLog);

    void updateMailsendLog(@Param("id") String id, @Param("status") int status);

    List<MailSendLog> getStudentByStatusTryTime();

    void updateCount(@Param("date") Date date, @Param("msgId") String msgId);

    Student getStudentByMsgId(@Param("empId") String empId);

    void insertInfomation(Infomation infomation);

    Long ainsertInfomation(Infomation build);
}
