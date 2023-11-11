package com.rabbiter.dm.vo;

/**
 * @author xianxian
 * @date 2021-12-08
 */
public class Result<T> {
    private Integer statusCode;
    private String msg;

    private T data;

    public static <T> Result<T> ok(String msg) {
        Result<T> result = new Result<>();
        result.statusCode = 200;
        result.msg = msg;
        return result;
    }

    public static <T> Result<T> ok() {
        Result<T> result = new Result<>();
        result.statusCode = 200;
        result.msg = "请求成功";
        return result;
    }

    public static <T> Result<T> fail(Integer statusCode, String msg) {
        Result<T> result = new Result<>();
        result.statusCode = statusCode;
        result.msg = msg;
        return result;
    }

    public Result<T> add(T data) {
        this.data = data;
        return this;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "statusCode=" + statusCode +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
