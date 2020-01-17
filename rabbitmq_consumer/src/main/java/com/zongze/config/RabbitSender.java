package com.zongze.config;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * Create By xzz on 2019/3/30
 */
public class RabbitSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback{

    private static Logger logger = LoggerFactory.getLogger(RabbitSender.class);

    private RabbitTemplate rabbitTemplate;


    public void sendMessage(String exchange, String routingKey, Object object) {
        rabbitTemplate.convertAndSend(exchange, routingKey, JSON.toJSONString(object));
    }


    public void sendMessage(String exchange, String routingKey, Object object, long expire) {
        rabbitTemplate.convertAndSend(exchange, routingKey, JSON.toJSONString(object), message -> {
            message.getMessageProperties().setExpiration(String.valueOf(expire));
            return message;
        });
    }

    public RabbitSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    /**
     * 消息发送到rabbitmq服务器回调
     * <p>
     * 如果消息没有到exchange,则confirm回调,ack=false
     * 如果消息到达exchange,则confirm回调,ack=true
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (!ack) {
            logger.info("==============消息发送失败==========");
        } else {
            logger.info("==============成功发送到交换机==========");
        }
    }


    /**
     * 交换机没有成功发送到队列有会掉
     */
    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        logger.info("message:[{}] i={};s={};s1={};s2={}", message.toString(), i, s, s1, s2);
        logger.info("==============消息发送到队列失败==========");
    }


}
