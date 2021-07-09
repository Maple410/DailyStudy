package com.ws.interceptor;

import com.ws.utils.TenantContextHolder;
import com.ws.utils.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: wangshuo
 * @Date: 2021/7/9 14:16
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String unionId = request.getHeader("UNIONID");

        log.info("LoginInterceptor context unionId:{}", unionId);

        if (null != unionId && !"null".equalsIgnoreCase(unionId)) {

            UserInfo dto = new UserInfo();//获取当前登陆人信息
            if (dto != null) {
                request.setAttribute("userInfo", dto);
                TenantContextHolder.setLoginUser(dto);

            }
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
