package com.ws.log.record;

import lombok.Data;

import java.util.Date;

/**
 * @Author: wangshuo
 * @Date: 2021/10/8 16:34
 */
@Data
public class SysLog {

    private Long id;

    private String userId;

    private String message;

    private String operation;

    private String method;

    private String params;

    private String ip;

    private Date createDate;

    private Long totalTime;

}
