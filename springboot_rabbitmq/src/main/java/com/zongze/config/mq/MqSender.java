package com.zongze.config.mq;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * Create By xzz on 2019/3/30
 */
public class MqSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private RabbitTemplate amqpTemplate;
    private static Logger logger = LoggerFactory.getLogger(MqSender.class);


    public String send(String exchange, String routKey, Object object) {
        return convertAndSend(exchange, routKey, object, false, null);
    }

    public String send(String exchange, String routKey, Object object, boolean persistent) {
        return convertAndSend(exchange, routKey, object, persistent, null);
    }

    public String send(String exchange, String routKey, Object object, String ttlTime) {
        return convertAndSend(exchange, routKey, object, false, ttlTime);
    }

    public String send(String exchange, String routKey, Object object, boolean persistent, String ttlTime) {
        return convertAndSend(exchange, routKey, object, persistent, ttlTime);
    }

    /**
     * exchange 交换机
     * routKey 路由键
     * object 消息内容
     * messageId 消息id
     * persistent 消息是否持久化
     * ttlTime 消息过期时间
     *
     * @param:
     * @return:
     */
    private String convertAndSend(String exchange, String routKey, Object object, boolean persistent, String ttlTime) {
        MessagePostProcessor messagePostProcessor = null;
        if (persistent || StringUtils.isNotBlank(ttlTime)) {
            messagePostProcessor = message -> {
                MessageProperties properties = message.getMessageProperties();
                if (persistent) {
                    properties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                }
                if (StringUtils.isNotBlank(ttlTime)) {
                    properties.setExpiration(ttlTime);
                }
                return message;
            };
        }
        if (null == messagePostProcessor) {
            amqpTemplate.convertAndSend(exchange, routKey, JSON.toJSONString(object), new CommonCorrelationData(object, exchange, routKey,null,false));
        } else {
            amqpTemplate.convertAndSend(exchange, routKey, JSON.toJSONString(object), messagePostProcessor, new CommonCorrelationData(object, exchange, routKey,ttlTime,persistent));
        }
        return "success";
    }


    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (!ack) {
            CommonCorrelationData commonCorrelationData = (CommonCorrelationData) correlationData;
            logger.info("消息发送失败重新发送，params:{}", JSON.toJSONString(commonCorrelationData));
            convertAndSend(commonCorrelationData.getExchange(), commonCorrelationData.getRoutKey(), commonCorrelationData.getMessageBody(),
                    commonCorrelationData.isPersistent(), commonCorrelationData.getTtlTime());
        } else {
            logger.info("消息发送成功，params:{}", JSON.toJSONString(correlationData));
        }
    }


    /**
     * 交换机没有成功发送到队列有回调
     */
    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        logger.info("message:[{}] i={};s={};s1={};s2={}", message.toString(), i, s, s1, s2);
    }

    public void setAmqpTemplate(RabbitTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

}
