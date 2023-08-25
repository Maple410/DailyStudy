package com.ws.retry;

import java.util.List;

/**
 * @Author: wangshuo
 * @Date: 2023/8/25 11:52
 */
public interface IRetryService {

    List<Integer> getOutSourceResult(String data);
}
