package com.ws.domains;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * @Author: wangshuo
 * @Date: 2021/7/9 13:51
 */

@Data
public class BasePageQuery extends PageableRequest {

    @ApiModelProperty(value = "参数")
    private Map<String, Object> params;

    @ApiModelProperty(value = "排序字段，逗号分隔")
    private String field;

    @ApiModelProperty(value = "顺序/倒叙，同字段一一对应")
    private String order;
}
