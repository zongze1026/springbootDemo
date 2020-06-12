package com.zongze.component;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleIllegalParamException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String tips = "参数不合法";
        if (errors.size() > 0) {
            tips = errors.get(0).getDefaultMessage();
        }
        return tips;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handlerNullParamException(HttpMessageNotReadableException e) {
        return "请求参数不能为空";
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handlerNullParamException(HttpRequestMethodNotSupportedException e) {
        return "不允许"+"["+e.getMethod()+"]请求";
    }


    @ExceptionHandler(Exception.class)
    public String exceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return "服务器异常";
    }
}
