package com.zongze.config.mq;

import com.alibaba.fastjson.JSON;
import com.zongze.entity.ResultResp;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * Create By xzz on 2019/3/30
 */
public class MqSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private AmqpTemplate amqpTemplate;
    private static Logger logger = LoggerFactory.getLogger(MqSender.class);


    public ResultResp convertAndSend(String exchange, Object content) {
        return send(exchange, null, content, null);
    }

    public ResultResp convertAndSend(String exchange, String routKey, Object content) {
        return send(exchange, routKey, content, null);
    }

//    public ResultResp convertAndSend(String exchange, Object content, String dieTime) {
//        return send(exchange, null, content, dieTime);
//    }


    public ResultResp convertAndSend(String exchange, String routKey, Object content, String dieTime) {
        return send(exchange, routKey, content, dieTime);
    }


    public ResultResp send(String exchange, String routKey, Object object, String dieTime) {
        String content = JSON.toJSONString(object);
        logger.info("消息入参：{}", content);
        if (StringUtils.isNotBlank(routKey) && StringUtils.isNotBlank(dieTime)) {
            amqpTemplate.convertAndSend(exchange, routKey, content, message -> {
                message.getMessageProperties().setExpiration(dieTime);
                return message;
            });
        } else if (StringUtils.isNotBlank(routKey)) {
            amqpTemplate.convertAndSend(exchange, routKey, content);
        } else if (StringUtils.isNotBlank(dieTime)) {
            amqpTemplate.convertAndSend(exchange, "", content, message -> {
                message.getMessageProperties().setExpiration(dieTime);
                return message;
            });
        } else {
            amqpTemplate.convertAndSend(exchange, "", content);
        }
        return ResultResp.succ();
    }


    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (!ack) {
            logger.info("==============消息发送失败==========");
        } else {
            logger.info("==============消息发送成功==========");
        }
    }


    /**
     * 交换机没有成功发送到队列有回调
     */
    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        logger.info("message:[{}] i={};s={};s1={};s2={}", message.toString(), i, s, s1, s2);
    }

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }


}
