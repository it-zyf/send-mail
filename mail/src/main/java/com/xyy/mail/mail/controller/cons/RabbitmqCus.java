package com.xyy.mail.mail.controller.cons;

import com.xyy.mail.mail.entity.Student;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

/**
 * @author yayu
 * @title: RabbitmqCus
 * @description: TODO rabbitmq消费方
 * @date 2020/9/1715:47
 */
@Component
@RabbitListener(queues = "xyy")
public class RabbitmqCus {
    @Autowired
    private JavaMailSender javaMailSender;

    @RabbitHandler
    public void test(Student mes) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试邮件");
        helper.setFrom("761769578@qq.com");
        helper.setTo(mes.getImage());
        helper.setSentDate(new Date());
        helper.setText("这是测试邮件的正文");
        //添加附件
        helper.addAttachment("123.jpg", new File("C:\\Users\\yayu\\Pictures\\Camera Roll\\wallhaven-xlq8xv_1920x1080.png"));
        javaMailSender.send(mimeMessage);
        System.out.println(mes.getName());
    }
}
