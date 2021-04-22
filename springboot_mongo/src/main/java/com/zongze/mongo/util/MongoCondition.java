package com.zongze.mongo.util;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2021/3/25 15:42
 * @Created by xiezz
 */
public class MongoCondition {
    private static Integer pageNum;
    private static Integer pageSize;
    private static Map<String, String> conditions;
    private static Map<String, OrderModel> orderConditions;


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, String> getConditions() {
        return conditions;
    }

    public void setConditions(Map<String, String> conditions) {
        this.conditions = conditions;
    }

    public static Map<String, OrderModel> getOrderConditions() {
        return orderConditions;
    }

    public static void setOrderConditions(Map<String, OrderModel> orderConditions) {
        MongoCondition.orderConditions = orderConditions;
    }

    public static MongoConditionBuilder newConditionBuilder() {
        return new MongoConditionBuilder();
    }


    public static class MongoConditionBuilder {
        private Integer pageNum;
        private Integer pageSize;
        private Map<String, String> conditions;
        private Map<String, OrderModel> orderConditions;


        public MongoConditionBuilder addCondition(String field, String value) {
            if (CollectionUtils.isEmpty(conditions)) {
                this.conditions = new HashMap<>();
            }
            this.conditions.put(field, value);
            return this;
        }


        public MongoConditionBuilder addOrderCondition(String field, OrderModel orderModel) {
            if (CollectionUtils.isEmpty(this.orderConditions)) {
                this.orderConditions = new HashMap<>();
            }
            this.orderConditions.put(field, orderModel);
            return this;
        }


        public MongoConditionBuilder addPageCondition(Integer pageNum, Integer pageSize) {
            this.pageNum = pageNum;
            this.pageSize = pageSize;
            return this;
        }

        public MongoCondition build() {
            MongoCondition mongoCondition = new MongoCondition();
            if (!CollectionUtils.isEmpty(conditions)) {
                mongoCondition.setConditions(conditions);
            }
            if (!CollectionUtils.isEmpty(orderConditions)) {
                mongoCondition.setOrderConditions(orderConditions);
            }
            if (!ObjectUtils.isEmpty(pageSize) && !ObjectUtils.isEmpty(pageNum)) {
                mongoCondition.setPageNum(pageNum);
                mongoCondition.setPageSize(pageSize);
            }
            return mongoCondition;
        }


    }


    public static enum OrderModel {
        ASC("asc", "升序"),
        DESC("desc", "降序");

        private String order;
        private String desc;

        OrderModel(String order, String desc) {
            this.order = order;
            this.desc = desc;
        }

        public String getOrder() {
            return order;
        }

        public String getDesc() {
            return desc;
        }
    }


}
