package com.shareblog.exception;

import com.shareblog.response.MsgResponse;
import com.shareblog.response.Status;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ExceptionHandler
 * @Author 杨武军
 * @Date 2020/5/16 11:11
 */
@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public MsgResponse hanler(BaseException e){
        e.printStackTrace();
        return new MsgResponse(e.getStatus(),e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public MsgResponse hanler(Exception e){
        e.printStackTrace();
        return new MsgResponse(Status.FAILED,e.getMessage());
    }
}
