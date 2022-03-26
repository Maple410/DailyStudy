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

        try {
            System.out.println(5/0);
        } catch (Exception e) {
            log.error("除零异常",e);
        }

      /*  List<Integer> allList = new ArrayList<>();
        allList.add(1);
        allList.add(2);
        allList.add(3);
        allList.add(4);
        allList.add(5);
        allList.add(6);

        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(6);
        allList.removeAll(list);

        System.out.println(allList);*/

        System.out.println("A"+ "\n" + "B");
        //String s =  "\n";
        String s =  " ";
        System.out.println(StringUtils.isBlank(s));
        System.out.println(StringUtils.isEmpty(s));

    }
}
