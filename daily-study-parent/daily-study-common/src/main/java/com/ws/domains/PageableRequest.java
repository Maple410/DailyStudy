package com.ws.domains;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: wangshuo
 * @Date: 2021/6/25 14:06
 */
@Data
public class PageableRequest {

    @ApiModelProperty(value = "页数")
    private Integer page = 1;

    @ApiModelProperty(value = "页面大小")
    private Integer pageSize = 10;

}
