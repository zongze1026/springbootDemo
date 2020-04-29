package com.zongze.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * Create By xzz on 2019/3/30
 */
public class MessageListener implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private static Logger logger = LoggerFactory.getLogger(MessageListener.class);


    /**
     * 消息发送到rabbitmq服务器回调
     * <p>
     * 如果消息没有到exchange,则confirm回调,ack=false
     * 如果消息到达exchange,则confirm回调,ack=true
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("correlationData:[{}]", correlationData);
        logger.info("cause:{}", cause);
        if (!ack) {
            logger.info("==============消息发送失败==========");
        } else {
            logger.info("==============消息发送成功==========");
        }
    }


    /**
     * 交换机没有成功发送到队列有会掉
     */
    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        logger.info("message:[{}] i={};s={};s1={};s2={}", message.toString(), i, s, s1, s2);
    }

}
