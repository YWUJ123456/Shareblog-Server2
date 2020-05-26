package com.shareblog.utils;

import com.shareblog.exception.BaseException;
import com.shareblog.response.Status;

/**
 * @ClassName ExceptionUtil
 * @Author 杨武军
 * @Date 2020/5/13 8:22
 */
public class ExceptionUtil {
    public static void idIsNull(){
        throw new BaseException(Status.FAILED,"id不能为空");
    }
}
