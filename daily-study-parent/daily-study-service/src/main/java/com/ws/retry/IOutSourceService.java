package com.ws.retry;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @Author: wangshuo
 * @Date: 2023/8/25 11:48
 */
public interface IOutSourceService {

    List<Integer> getResult() throws RuntimeException;
}
