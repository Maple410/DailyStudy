package com.ws.excel.service.impl;

import com.google.common.collect.Lists;
import com.ws.excel.exportor.DownLoadUtil;
import com.ws.excel.exportor.ExcelParam;
import com.ws.excel.exportor.vo.UserInfoExport;
import com.ws.excel.importor.ImportUtil;
import com.ws.excel.importor.vo.UserInfoImport;
import com.ws.excel.service.IExcelService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: wangshuo
 * @Date: 2020/12/11 11:19
 */
@Service
public class ExcelServiceImpl implements IExcelService {

    @Override
    public void exportExcel(HttpServletResponse response) throws Exception {
        String fileName = "测试.xls";
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        //前端 再对文件名进行URLDecoder 解码
        response.setHeader("Content-disposition", "attachment; filename=\"" + java.net.URLEncoder.encode(fileName, "UTF-8") + "\"");
        // 定义输出类型
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream out = response.getOutputStream();

        List<UserInfoExport> exports = new ArrayList<>();
        UserInfoExport export = UserInfoExport.builder().name("小明").Age(18).address("青岛").build();
        exports.add(export);

        List<Class> classList = Lists.newArrayList(UserInfoExport.class);
        List<String> sheetNames = Lists.newArrayList("导出");
        List<Collection> dataSets = new ArrayList<>();
        dataSets.add(exports);

        ExcelParam excelParam = new ExcelParam();
        excelParam.setClassList(classList);
        excelParam.setSheetNames(sheetNames);
        excelParam.setOut(out);
        excelParam.setDataSets(dataSets);
        DownLoadUtil.exportExcel(excelParam);
        out.close();
    }

    @Override
    public List<UserInfoImport> importExcel(MultipartFile file) {

        List<UserInfoImport> infoList = ImportUtil.readExcel("", UserInfoImport.class, file);
        return infoList;
    }
}
