package com.zongze.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Create By xzz on 2018/11/21
 * 继承路由接口，自己实现数据源动态切换
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected String determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getKey();
    }
}
