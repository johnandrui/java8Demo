package com.john.www.model.result;


import com.alibaba.fastjson.JSON;

/**
 * @Author:""
 * @Description: 统一API响应结果封装
 * @Date: 10:07 2018/8/13
 * @Modified By:
 */
public class Result<T> {

    /**
     * 调用结果码
     */
    private Integer code;

    /**
     * 调用结果说明
     */
    private String message;

    /**
     * 调用结果详细说明
     */
    private String detail;

    /**
     * 返回的数据
     */
    private T data;

    public static Result success() {
        return new Result(ResultCode.SUCCESS);
    }

    public static <T> Result success(T data) {
        return new Result<T>(ResultCode.SUCCESS, data, null);
    }

    public Result() {
    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Result(ResultCode resultCode, T data, String detail) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
        this.detail = detail;
    }

    public Result setResultCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean checkSuccess() {
        if (null != this.code && this.code == 0) {
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
