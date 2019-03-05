package com.zongze.controller;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Create By xzz on 2019/1/30
 */
@ControllerAdvice
public class GlobalExceptionHander {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHander.class);


    @ExceptionHandler(Exception.class)
    public String hander(Exception e){
        logger.error("exception message:{}",e.getMessage());
        e.printStackTrace();

        if(e instanceof UnauthorizedException){
            return "noPerm";
        }else if (e instanceof UnauthenticatedException){
            return "unAuth";
        }
        return "error";
    }


}
