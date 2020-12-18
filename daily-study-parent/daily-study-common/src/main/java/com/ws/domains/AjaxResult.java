package com.ws.domains;

import com.ws.domains.enums.ResultCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: wangshuo
 * @Date: 2020/12/18 15:15
 */
@Data
public class AjaxResult<T> implements Serializable {

    private static final Long serialVersionUID = 5757933746700705620L;

    private T result;

    private String msg;

    private String code;

    private String cause;

    public AjaxResult() {
    }

    public AjaxResult(T result) {
        this.result = result;
    }

    public AjaxResult(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }


    public AjaxResult<T> result(T result) {
        this.result = result;
        return this;
    }

    public AjaxResult<T> success() {
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.msg = ResultCodeEnum.SUCCESS.getMessage();
        return this;
    }

    public AjaxResult<T> fail() {
        this.code = ResultCodeEnum.ERROR_UNKNOW.getCode();
        this.msg = ResultCodeEnum.ERROR_UNKNOW.getMessage();
        return this;
    }

    public AjaxResult<T> fail(String message) {
        this.code = ResultCodeEnum.ERROR_UNKNOW.getCode();
        this.msg = message;
        return this;
    }

    public AjaxResult<T> fail(String code, String message) {
        this.code = code;
        this.msg = message;
        return this;
    }

    public AjaxResult<T> cause(String cause) {
        this.cause = cause;
        return this;
    }

}
