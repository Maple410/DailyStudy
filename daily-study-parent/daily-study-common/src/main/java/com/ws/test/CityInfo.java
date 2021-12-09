package com.ws.test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Author: wangshuo
 * @Date: 2021/12/8 15:16
 */
@Data
public class CityInfo {

    private Integer id;

    private Integer parentId;

    private String name;

    private List<CityInfo> childrenList;

    public CityInfo(Integer id, Integer parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }
}
