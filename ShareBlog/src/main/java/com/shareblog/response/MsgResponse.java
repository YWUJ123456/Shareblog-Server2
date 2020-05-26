package com.shareblog.response;

import lombok.Data;

/**
 * @ClassName MsgResponse
 * @Author 杨武军
 * @Date 2020/5/11 14:24
 *
 * 返回结果类
 */
@Data
public class MsgResponse {
    //状态码
    private Integer status;
    //提示信息
    private String msg;
    //响应数据
    private Object data;

    public MsgResponse(){

    }
    public MsgResponse(Integer status, String msg){
        this.status = status;
        this.msg = msg;
    }
    public MsgResponse(Integer status, String msg, Object data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
}
