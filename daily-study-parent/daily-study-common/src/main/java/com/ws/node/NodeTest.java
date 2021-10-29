package com.ws.node;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: sq
 * @Date: 2021/10/28 17:47
 */
public class NodeTest {


    public static void main(String[] args) {


        List<Company> list = new ArrayList<>();
        list.add(new Company(1L,0L,"南京"));
        list.add(new Company(2L,1L,"北京"));
        list.add(new Company(3L,1L,"天津"));
        list.add(new Company(4L,3L,"郑州"));
        list.add(new Company(5L,3L,"青岛"));
        list.add(new Company(6L,3L,"厦门"));

        System.out.println(ForestNodeMerge.merge(list));
    }
}
