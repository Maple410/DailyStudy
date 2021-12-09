package com.ws.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: wangshuo
 * @Date: 2021/12/8 15:17
 *
 * list 转tree 前提 是要知道根节点
 */
public class TestTree {


    public static void main(String[] args) {

        List<CityInfo> cityList = new ArrayList<>();

        cityList.add(new CityInfo(0,1110,"中国"));
        cityList.add(new CityInfo(1, 0, "山东省"));
        cityList.add(new CityInfo(2, 1, "青岛市"));
        cityList.add(new CityInfo(3, 1, "济南市"));
        cityList.add(new CityInfo(4, 1, "烟台市"));
        cityList.add(new CityInfo(5, 2, "黄岛区"));
        cityList.add(new CityInfo(6, 2, "即墨市"));
        cityList.add(new CityInfo(7, 5, "团结街道"));
        cityList.add(new CityInfo(8, 0, "江苏省"));
        cityList.add(new CityInfo(9, 8, "南京市"));
        cityList.add(new CityInfo(10, 9, "雨花区"));


        Map<Integer,List<CityInfo>> map = cityList.stream().collect(Collectors.groupingBy(CityInfo::getParentId));

        cityList.forEach(item ->{
            item.setChildrenList(map.get(item.getId()));
        });
        List<CityInfo> resultList = cityList.stream().filter(item ->item.getParentId().equals(1110)).collect(Collectors.toList());
        System.out.println(resultList.toString());



    }
}
