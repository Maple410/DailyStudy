package com.ws.operation.log;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: sq
 * @Date: 2021/10/29 11:02
 */
@Data
public class ChangeVo {


    @ApiModelProperty(value = "字段描述")
    private String fieldDescribe;

    @ApiModelProperty(value = "变更前属性值")
    private String changeNowValue;

    @ApiModelProperty(value = "变更后属性值")
    private String changeHistoryValue;

}
