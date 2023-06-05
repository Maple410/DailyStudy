package com.ws.test;

import com.google.common.collect.Lists;
import com.ws.excel.exportor.vo.UserInfoExport;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: wangshuo
 * @Date: 2020/12/18 10:46
 */
public class TestLambda {

    public static void main(String[] args) {


        List<UserInfoExport> infoList = new ArrayList<>();
        infoList.add(UserInfoExport.builder().name("张三").Age(18).address("南京").build());
        infoList.add(UserInfoExport.builder().name("李四").Age(22).address("烟台").build());
        infoList.add(UserInfoExport.builder().name("王五").Age(32).address("青岛").build());
        infoList.add(UserInfoExport.builder().name("赵六").Age(44).address("烟台").build());


        //过滤
        System.out.println("-------------过滤-----------------");
        List<UserInfoExport> filterList = infoList.stream().filter(item -> item.getAge() == 1).collect(Collectors.toList());
        System.out.println(filterList.size());

        //排序
        System.out.println("-------------排序-----------------");
        List<UserInfoExport> sortList = infoList.stream().sorted(Comparator.comparing(UserInfoExport::getAge).reversed()).collect(Collectors.toList());
        System.out.println(sortList.toString());


        //1.按照对象的某个属性分组，并取其中某个属性的集合：按照id 分组 取address的list
        System.out.println("-------------分组-----------------");
        Map<Integer, List<String>> map = infoList.stream().collect(Collectors.groupingBy(UserInfoExport::getAge, Collectors.mapping(UserInfoExport::getAddress, Collectors.toList())));
        map.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });

        //2.保持分组后有序
        System.out.println("-------------分组后有序-----------------");
        Map<Integer, List<UserInfoExport>> sortMap = infoList.stream().collect(Collectors.groupingBy(UserInfoExport::getAge, LinkedHashMap::new, Collectors.toCollection(ArrayList::new)));
        sortMap.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });

        //3.分隔
        System.out.println("-------------分隔-----------------");
        List<List<UserInfoExport>> splitLlist = Lists.partition(infoList, 2);
        splitLlist.forEach(item -> {
            System.out.println(item.toString());
        });

        //4.属性-实体对应
        System.out.println("-------------属性实体对应-----------------");
        Map<String, UserInfoExport> userMap = infoList.stream().collect(Collectors.toMap(UserInfoExport::getName, item -> item));
        userMap.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });

        //5 取其中一个属性构成list
        List<String> nameList = infoList.stream().map(item -> item.getName()).collect(Collectors.toList());
        nameList.forEach(item -> {
            System.out.println(item);
        });

        // reduce 聚合函数
        System.out.println("给定个初始值，求和");
        System.out.println(Stream.of(1, 2, 3, 4).reduce(100, (sum, item) -> sum + item));
        System.out.println(Stream.of(1, 2, 3, 4).reduce(100, Integer::sum));
        System.out.println("给定个初始值，求min");
        System.out.println(Stream.of(1, 2, 3, 4).reduce(100, (min, item) -> Math.min(min, item)));
        System.out.println(Stream.of(1, 2, 3, 4).reduce(100, Integer::min));
        System.out.println("给定个初始值，求max");
        System.out.println(Stream.of(1, 2, 3, 4).reduce(100, (max, item) -> Math.max(max, item)));
        System.out.println(Stream.of(1, 2, 3, 4).reduce(100, Integer::max));

        System.out.println("无初始值，求和");
        System.out.println(Stream.of(1, 2, 3, 4).reduce(Integer::sum).orElse(0));
        System.out.println("无初始值，求max");
        System.out.println(Stream.of(1, 2, 3, 4).reduce(Integer::max).orElse(0));
        System.out.println("无初始值，求min");
        System.out.println(Stream.of(1, 2, 3, 4).reduce(Integer::min).orElse(0));

        System.out.println( Arrays.stream(new int[]{1,2,3,4,3,2,1}).reduce((a,b)->a^b).getAsInt());
        System.out.println(Stream.of(1,2,3,2,3).reduce((a,b) ->a^b).orElse(0));


    }
}
