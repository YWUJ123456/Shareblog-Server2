package com.shareblog.exception;

import lombok.Data;

/**
 * @ClassName BaseException
 * @Author 杨武军
 * @Date 2020/5/11 15:55
 *
 * 自定义异常
 */
@Data
public class BaseException extends RuntimeException{
    private Integer status;
    public BaseException(Integer status, String msg){
        super(msg);
        this.status = status;
    }
}
