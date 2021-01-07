package com.xyy.mail.mail.task;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Snowflake;
import com.xyy.mail.mail.entity.Infomation;
import com.xyy.mail.mail.entity.MailConstants;
import com.xyy.mail.mail.entity.MailSendLog;
import com.xyy.mail.mail.entity.Student;
import com.xyy.mail.mail.service.MailSendLogService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author yayu
 * @title: MailSendTask
 * @description: TODO 查询数据库,对status是0的进行邮件的再次发送.
 * @date 2020/9/1814:13
 */
@Component
public class MailSendTask {
    @Autowired
    private MailSendLogService mailSendLogService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Snowflake snowflake;

    //    @Scheduled(cron = "0/10 * * * * ?")
    public void mailSendTask() {
        List<MailSendLog> mailsendLogList = mailSendLogService.getStudentByStatusTryTime();
        if (CollUtil.isEmpty(mailsendLogList)) {
            return;
        }
        mailsendLogList.forEach(mailSendLog -> {
            //如果尝试次数大于等于三次.直接设置状态为失败.2
            if (mailSendLog.getCount() >= 3) {
                mailSendLogService.updateMailsendLog(mailSendLog.getMsgId(), 2);
            } else {
                mailSendLogService.updateCount(new Date(), mailSendLog.getMsgId());
                //查出当前信息
                Student student = mailSendLogService.getStudentByMsgId(mailSendLog.getEmpId());
                rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME, student, new CorrelationData(mailSendLog.getMsgId()));
            }
        });


    }

//    @Scheduled(cron = "0 */1 * * * ?")
    public void mailDeleteTask() {
        Infomation infomationg = Infomation.builder().age(12).name("xyy").sex("男").photo("C:\\Users\\yayu\\Pictures\\Camera Roll\\46509166.jpg").id(String.valueOf(snowflake.nextId())).time(new Date()).build();
        mailSendLogService.insertInfomation(infomationg);
    }


}
