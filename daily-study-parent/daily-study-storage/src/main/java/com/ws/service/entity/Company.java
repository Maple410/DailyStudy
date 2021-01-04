package com.ws.service.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: wangshuo
 * @Date: 2021/1/4 14:19
 */

@Data
@TableName("company")
public class Company {

    private Integer id;

    private String address;

    private String name;

    private String shareholder;

}
