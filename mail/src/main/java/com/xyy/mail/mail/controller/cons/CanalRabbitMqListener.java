package com.xyy.mail.mail.controller.cons;

import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import com.xyy.mail.mail.entity.CanalMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.messaging.Message;

import java.io.IOException;

/**
 * @author: zyf
 * @create: 2022-01-05 15:29
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class CanalRabbitMqListener {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "canal.queue", durable = "true"),
                    exchange = @Exchange(value = "canal.exchange"),
                    key = "canal.routing.key"
            )
    })
    public void handleDataChange(String message, Message mes, Channel channel) throws IOException {
        //将message转换为CanalMessage
        CanalMessage canalMessage = JSONUtil.toBean(message, CanalMessage.class);
        String tableName = canalMessage.getTable();
        log.info("Canal 监听 {} 发生变化；明细：{}", tableName, message);
        //TODO 业务逻辑自己完善...............
        if("delete".equalsIgnoreCase(canalMessage.getType())){
            System.out.println("有人删除表中数据");
        }
        //告知已消费
        Long deliverTag  = (Long) mes.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliverTag, false);


    }
}
