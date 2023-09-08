package com.ws.multi.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: wangshuo
 * @Date: 2023/9/8 14:46
 *
 * 出现死锁问题   https://juejin.cn/post/7212603037666902077
 */
public class TestConcurrentMapDeadlock {

    public static void main(String[] args) {
        Map<String, Integer> concurrentHashMap1 = new ConcurrentHashMap<>(16);
        Map<String, Integer> concurrentHashMap2 = new ConcurrentHashMap<>(16);

        new Thread(() -> concurrentHashMap1.computeIfAbsent("a", key -> {
            concurrentHashMap2.computeIfAbsent("b", key2 -> 2);
            return 1;
        })).start();

        new Thread(() -> concurrentHashMap2.computeIfAbsent("b", key -> {
            concurrentHashMap1.computeIfAbsent("a", key2 -> 2);
            return 1;
        })).start();
    }

}
