package com.xyy.mail.mail.controller.cons;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

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
