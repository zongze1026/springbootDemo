package com.zongze.component;

import com.zongze.entity.ExceptionEnum;
import com.zongze.entity.ResultResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Create By xzz on 2019/3/27
 */

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultResp handleIllegalParamException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String tips = "参数不合法";
        if (errors.size() > 0) {
            tips = errors.get(0).getDefaultMessage();
        }
        return ResultResp.fail(tips);
    }


    @ExceptionHandler(Exception.class)
    public ResultResp exceptionHandler(HttpServletRequest request, Exception e) {
        logger.info("请求服务器异常", e);
        return ResultResp.fail(ExceptionEnum.SYSTEM_ERROR);
    }
}
