package com.ws.service.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: wangshuo
 * @Date: 2022/3/30 11:35
 */
@Data
@AllArgsConstructor
public class School {

    private Integer id;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "名称")
    private String name;
}
