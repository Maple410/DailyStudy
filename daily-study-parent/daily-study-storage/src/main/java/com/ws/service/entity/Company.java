package com.ws.service.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ws.annotation.Remarks;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: wangshuo
 * @Date: 2021/1/4 14:19
 */

@Data
@TableName("company")
public class Company {

    private Integer id;

    @Remarks(name = "变更公司地址:")
    private String address;

    @Remarks(name = "变更公司名称:")
    private String name;

    private String shareholder;

    @Remarks(name = "变更创建时间",dateTimeFlag = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
