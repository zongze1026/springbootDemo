package com.zongze.service.impl;

import com.zongze.entity.Logger;
import com.zongze.mapper.LogDao;
import com.zongze.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create By xzz on 2019/3/26
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public boolean add(Logger log) {
        return logDao.add(log) == 1 ? true : false;
    }
}
