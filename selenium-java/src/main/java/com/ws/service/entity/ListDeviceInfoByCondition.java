package com.ws.service.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: wangshuo
 * @Date: 2021/2/25 11:00
 */
@Data
@TableName("listdeviceinfobycondition")
public class ListDeviceInfoByCondition {

    private Integer id;

    private String areaCode;

    private String deviceType;

    private String machineNumber;

    private String nsrsbh;

    private String onlineStatus;

    private String phoneNo;

    private String usbCompanyName;

    private String companyName;

    private Integer page;

    private Integer size;
}
