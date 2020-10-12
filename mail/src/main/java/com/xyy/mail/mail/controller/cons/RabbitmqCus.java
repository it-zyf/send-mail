package com.xyy.mail.mail.controller.cons;

import com.rabbitmq.client.Channel;
import com.xyy.mail.mail.entity.MailConstants;
import com.xyy.mail.mail.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author yayu
 * @title: RabbitmqCus
 * @description: TODO rabbitmq消费方 通过redis记录过滤掉重复消费.
 * @date 2020/9/1715:47
 */
@Component
public class RabbitmqCus {
    public static final Logger logger = LoggerFactory.getLogger(RabbitmqCus.class);
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private RedisTemplate redisTemplate;

    @RabbitHandler
    @RabbitListener(queues = MailConstants.MAIL_QUEUE_NAME)
    public void test(Message mes, Channel channel) throws IOException {
        Student student = (Student) mes.getPayload();
        MessageHeaders headers = mes.getHeaders();
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        String msgId = (String) headers.get("spring_returned_message_correlation");
        try {
            if (redisTemplate.opsForHash().entries("mail_log").containsKey(msgId)) {
                logger.info(msgId + ":消息已经被消费");
                channel.basicAck(tag, false);
                return;
            }
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject("WELL COME ");
            helper.setFrom("761769578@qq.com");
            helper.setTo(student.getImage());
            helper.setSentDate(new Date());
            helper.setText("爱要大声说出来!");
            //添加附件
            helper.addAttachment("123.jpg", new File("C:\\Users\\yayu\\Pictures\\Camera Roll\\wallhaven-xlq8xv_1920x1080.png"));
            javaMailSender.send(mimeMessage);
            redisTemplate.opsForHash().put("mail_log", msgId, "xyy");
            channel.basicAck(tag, false);
            logger.info("消息发送成功");
            System.out.println(student.getName());
        } catch (Exception e) {
            logger.info("消息发送失败");
            channel.basicNack(tag,false,true);
            e.printStackTrace();

        }
    }
}
