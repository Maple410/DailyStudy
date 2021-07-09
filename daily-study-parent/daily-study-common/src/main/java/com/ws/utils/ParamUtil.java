package com.ws.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: wangshuo
 * @Date: 2021/7/9 14:04
 */
@Slf4j
public class ParamUtil {

    public static String getUnionId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String unionid = request.getHeader("UNIONID");

        log.info("当前登录人 unionId:{}", unionid);
        if (StringUtils.isEmpty(unionid)) {
            throw new RuntimeException("UNIONID不能为空!");
        }
        return unionid;
    }
}
