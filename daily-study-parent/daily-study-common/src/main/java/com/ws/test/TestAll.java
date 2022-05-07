package com.ws.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: wangshuo
 * @Date: 2021/6/30 14:25
 */
@Slf4j
public class TestAll {


    public static void main(String[] args) {

        int[] arr = {1,2,3};
        List list =Arrays.asList(1,2,3);
        System.out.println(list.get(1));
        list.stream().forEach(System.out::println);

    }
}
