package com.zongze.ref;

import com.alibaba.fastjson.JSON;
import com.zongze.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.concurrent.*;

/**
 * Created by xieZZ on 2019/2/24
 */
@Setter
@Getter
public class Parent {

    private String nickName;


    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> scheduledFuture = service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行时间：" + DateUtil.format(Calendar.getInstance().getTime(),DateUtil.DATE_TIME));
            }
        }, 2, 5, TimeUnit.SECONDS);
        System.out.println(JSON.toJSONString(scheduledFuture));
    }


}
