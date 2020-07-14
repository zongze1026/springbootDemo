package com.zongze.rocketmq.produce;

import com.zongze.rocketmq.OrderService;
import com.zongze.rocketmq.entity.Order;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

import static com.zongze.rocketmq.entity.Constant.ORDER_GROUP;

/**
 * Create By xzz on 2020/7/14
 */
@RocketMQTransactionListener(txProducerGroup = ORDER_GROUP)
public class RocketMQTransactionOrderListener implements RocketMQLocalTransactionListener {

    @Autowired
    private OrderService orderService;


    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        Order order = (Order) o;
        try {
            System.out.println(order);
            System.out.println( message.getHeaders().get("transactionId"));
            int i = orderService.saveOrder(order);
            if (i > 0) {
                return RocketMQLocalTransactionState.COMMIT;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (order.getId() % 2 != 0 && order.getId() > 10) {
//            System.exit(-1);
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.ROLLBACK;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        System.out.println("事务进行了会查" + message.getHeaders().get("transactionId"));
        return RocketMQLocalTransactionState.COMMIT;
    }
}
