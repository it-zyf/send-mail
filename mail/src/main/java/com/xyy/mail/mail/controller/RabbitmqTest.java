package com.xyy.mail.mail.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yayu
 * @title: RabbitmqTest
 * @description: TODO
 * @date 2020/9/1715:34
 */
@RestController
@RequestMapping("/rabbitmq")
public class RabbitmqTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RequestMapping("/direct")
    public Map test(){
        rabbitTemplate.convertAndSend("xyy","123");
        return new HashMap<String, Object>(){{put("msg","成功");}};
    }
}
