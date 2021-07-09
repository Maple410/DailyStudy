package com.ws.utils;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.experimental.UtilityClass;

/**
 * @Author: wangshuo
 * @Date: 2021/7/9 14:19
 */
@UtilityClass
public class TenantContextHolder {


    private final ThreadLocal<UserInfo> LOGIN_USER_THREAD_LOCAL = new TransmittableThreadLocal<>();


    public void setLoginUser(UserInfo info) {
        LOGIN_USER_THREAD_LOCAL.set(info);
    }

    public UserInfo getLoginUser() {
        return LOGIN_USER_THREAD_LOCAL.get();
    }

    public void clearLoginUser() {
        LOGIN_USER_THREAD_LOCAL.remove();
    }

}
