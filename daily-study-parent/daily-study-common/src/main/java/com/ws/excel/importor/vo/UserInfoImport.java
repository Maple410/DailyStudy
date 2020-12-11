package com.ws.excel.importor.vo;

import com.ws.excel.importor.ExcelColumn;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: wangshuo
 * @Date: 2020/10/20 18:15
 */
@Data
public class UserInfoImport implements Serializable {

    @ExcelColumn(value = "姓名", col = 1)
    private String name;

    @ExcelColumn(value = "年龄", col = 2)
    private Integer Age;

    @ExcelColumn(value = "地址", col = 3)
    private String address;
}
