package com.zongze.constant;

/**
 * Create By xzz on 2018/11/21
 */
public enum DataSourceType {

    MASTER("master", "主数据源"),
    SLAVE("slave", "从数据源"),
    SLAVE_02("slave02", "从数据源"),
    ;

    /**
     * 数据源的key
     */
    private String key;

    DataSourceType(String key, String description) {
        this.key = key;
        this.description = description;
    }

    /**
     * 说明
     */
    private String description;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
