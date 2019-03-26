package com.zongze.controller;

import com.zongze.annotation.Log;
import com.zongze.entity.Logger;
import com.zongze.entity.ResultResp;
import com.zongze.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By xzz on 2019/3/26
 */
@Log(title = "用户管理")
@RestController
@RequestMapping("log")
public class LogController {


    @Autowired
    private LogService logService;

    @Log
    @PostMapping("add")
    public ResultResp addLog(@RequestBody Logger log) {
        logService.add(log);
        return ResultResp.succ(log);
    }


}
