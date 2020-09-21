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
 * @description: TODO rabbitmq测试
 * @date 2020/9/1715:47
 */
@Component
@RabbitListener(queues = "test")
public class RabbitmqCusTest {

    @RabbitHandler
    public void test(String mes) {
        System.out.println(mes);
    }
}
