package com.xyy.mail.mail.config;

import com.xyy.mail.mail.entity.MailConstants;
import com.xyy.mail.mail.service.MailSendLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yayu
 * @title: RabbitMqConfig
 * @description: TODO 自定义rabbitTemplate
 * @date 2020/9/1810:56
 */
@Configuration
public class RabbitMqConfig {
    public final static Logger logger = LoggerFactory.getLogger(RabbitMqConfig.class);
    @Autowired
    CachingConnectionFactory cachingConnectionFactory;

    @Autowired
    private MailSendLogService mailSendLogService;

    @Bean
    RabbitTemplate rabbitTemplate() {
        RabbitTemplate  rabbitTemplate= new RabbitTemplate(cachingConnectionFactory);
        //设置消息发送成功后回调
        /**
         * data 获取rabbitmq消息唯一id
         * ask 成功发送到rabbit服务器返回true
         *
         */
        rabbitTemplate.setConfirmCallback((data, ask, cause) -> {
            String id = data.getId();
            if (ask) {
                logger.info(id + "消息发送成功");
                //修改数据库中记录status 为1
                mailSendLogService.updateMailsendLog(id, 1);

            } else {
                logger.info(id + "消息发送失败");
            }
            //交换器到队列中消息发送失败,回调
            rabbitTemplate.setReturnCallback((msg, repCode, repText, exchange, routingkey) -> {
                logger.info("消息发送失败");
            });

        });
        return rabbitTemplate;
    }

    @Bean
    Queue queue(){
        return new Queue(MailConstants.MAIL_QUEUE_NAME,true);
    }

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange(MailConstants.MAIL_EXCHANGE_NAME,true,false);
    }

    @Bean
    Binding binding(){
        return BindingBuilder.bind(queue()).to(topicExchange()).with(MailConstants.MAIL_ROUTING_KEY_NAME);
    }




}
