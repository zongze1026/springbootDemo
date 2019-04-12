package com.zongze.receice;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.zongze.config.RabbitSender;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create By xzz on 2019/4/12
 */
@Service
public class Receive1 {

    @Autowired
    private RabbitSender rabbitSender;

    @RabbitListener(queues = "pub_work_queue1")
    public void work1(String content, Channel channel, Message message) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("接收到消息：" + content + ";时间：" + format.format(new Date()));
        MqEntity mqEntity = JSONObject.parseObject(content, MqEntity.class);

        if (mqEntity.getCount() > mqEntity.getMaxCount()) {
            System.out.println("最大重试次数" + mqEntity.getCount() + "结束发送");
            return;
        }
        mqEntity.setCount(mqEntity.getCount() + 1);
        rabbitSender.sendMessage("pub_send_exchange","pub_ttl_key",mqEntity,mqEntity.getTimeOut()*mqEntity.getCount());

    }


//    @RabbitListener(queues = "pub_work_queue2")
//    public void work2(String content, Channel channel, Message message) throws IOException {
//        System.out.println(content);
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//    }
//
//    @RabbitListener(queues = "pub_work_queue1")
//    public void work3(String content, Channel channel, Message message) throws IOException {
//        System.out.println(content);
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//    }


}
