package com.ws.forest;

import com.dtflys.forest.annotation.Get;

import java.util.Map;

/**
 * @Author: wangshuo
 * @Date: 2021/12/24 17:41
 */
public interface AmapClient {

    @Get("http://ditu.amap.com/service/regeo?longitude=${0}&latitude=${1}")
    public Map getLocation(String longitude, String latitude);
}
