package com.ws.excel.exportor;

import lombok.Data;

import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangshuo
 * @Date: 2020/10/15 15:35
 */
@Data
public class ExcelParam {

    private OutputStream out;

    // Map形式
    private String fileName;

    private String sheetName;

    private List<Map<String, String>> dataMapSets;

    private List<String> headers;

    //Collection形式

    private List<String> sheetNames;

    private List<Class> classList;

    private List<Collection> dataSets;



}
