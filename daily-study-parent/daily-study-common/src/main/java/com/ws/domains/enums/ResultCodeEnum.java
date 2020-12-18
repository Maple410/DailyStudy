package com.ws.domains.enums;

/**
 * @Author: wangshuo
 * @Date: 2020/12/18 15:19
 */
public enum ResultCodeEnum {

    SUCCESS("0", "成功"),
    ERROR_UNKNOW("101", "未知异常"),
    ERROR_PARAM("102", "参数异常"),
    ERROR_REMOTE_CALL("103", "远程调用异常");

    private String code;
    private String message;

    private ResultCodeEnum(String code, String message) {
        this.message = message;
        this.code = code;
    }

    public static String getMessage(String code) {
        ResultCodeEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ResultCodeEnum c = var1[var3];
            if (code.equals(c.getCode())) {
                return c.message;
            }
        }

        return null;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return this.code;
    }
}
