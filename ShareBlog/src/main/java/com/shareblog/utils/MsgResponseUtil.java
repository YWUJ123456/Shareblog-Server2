package com.shareblog.utils;

import com.shareblog.response.MsgResponse;
import com.shareblog.response.Status;

/**
 * @ClassName MsgResponseUtil
 * @Author 杨武军
 * @Date 2020/5/11 15:54
 *
 * 响应结果工具类
 */
public class MsgResponseUtil {
    public static MsgResponse sucess(String msg,Object data){
        return new MsgResponse(Status.SUCCESS,msg,data);
    }
    public static MsgResponse failed(String msg,Object data){
        return new MsgResponse(Status.FAILED,msg,data);
    }
    //增加
    public static MsgResponse addSucess(){
        return new MsgResponse(Status.SUCCESS,"添加成功");
    }
    public static MsgResponse addFailed(){
        return new MsgResponse(Status.FAILED,"添加失败");
    }
    //删除
    public static MsgResponse deleteSucess(){
        return new MsgResponse(Status.SUCCESS,"删除成功");
    }
    public static MsgResponse deleteFailed(){
        return new MsgResponse(Status.FAILED,"删除失败");
    }
    //更新
    public static MsgResponse updateSucess(){
        return new MsgResponse(Status.SUCCESS,"修改成功");
    }
    public static MsgResponse updateFailed(){
        return new MsgResponse(Status.FAILED,"修改失败");
    }
    //查询
    public static MsgResponse findSucess(Object data){
        return new MsgResponse(Status.SUCCESS,"查询成功",data);
    }
    public static MsgResponse findFailed(Object data){
        return new MsgResponse(Status.FAILED,"查询失败",null);
    }
}
