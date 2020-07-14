package com.zongze.rocketmq;
import com.zongze.rocketmq.entity.Order;
import org.springframework.stereotype.Service;

/**
 * Create By xzz on 2020/7/14
 */
@Service
public class OrderService {

    /**
     * 1.模拟添加订单服务;业务执行成功则返回大于零的值
     * 2.这里省去@Transactionl注解
     * @param order
     * @return int
     */
    public int saveOrder(Order order) {

        if (order.getId() % 2 == 0) {
            return order.getId();
        }
        //模拟业务失败
        return -1;
    }


}
