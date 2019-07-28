package com.john.www.model.exception;

import com.john.www.model.result.ResultCode;

/**
 * @Author:
 * 统一业务异常定义
 * 在我们代码，遇到需要抛出异常的地方，必须封装为此异常抛出
 * 异常主要包括三类：
 * 1. 系统级异常：例如数据库异常、文件I/O流程错误、服务内部错误等，此异常必须打印error级别日志
 * 2. 业务级公共异常： 例如用户鉴权失败、参数校验失败、json解析错误、java反射错误等，此异常必须打印warn级别以上日志
 * 3. 业务异常 ： 例如用户id不存在、用户添加时重复、删除的用户已被引用等，此异常建议全部打印info级别日志，根据需要也可以打印warn级别日志，禁止打印error级别日志
 * @Date: 18:13 2018/9/14
 * @Modified By:
 */
public class BusinessException extends RuntimeException {

    private ResultCode resultCode;

    public BusinessException() {
    }

    public BusinessException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public BusinessException(ResultCode resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }

    public BusinessException(ResultCode resultCode, String message, Throwable cause) {
        super(message, cause);
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

}
