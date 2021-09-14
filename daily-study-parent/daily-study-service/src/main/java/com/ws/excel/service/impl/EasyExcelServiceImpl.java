package com.ws.excel.service.impl;

import com.google.common.collect.Lists;
import com.ws.excel.easyexcel.ExcelUtil;
import com.ws.excel.easyexcel.UserEntity;
import com.ws.excel.service.IEasyExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class EasyExcelServiceImpl implements IEasyExcelService {


    @Override
    public void exportFix(HttpServletResponse response) {

        List<UserEntity> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserEntity entity = new UserEntity();
            entity.setName("张三" + i);
            entity.setAge(30 + i);
            entity.setTime(new Date(System.currentTimeMillis() + i));
            entity.setRate(0.87 + i);
            dataList.add(entity);
        }

        String fileName = "固定表头Excel导出";

        try {
            ExcelUtil.downloadFix(response, fileName, UserEntity.class, dataList,null);
        } catch (IOException e) {
            log.error("EasyExcel 固定表头Excel导出 失败：{}", e.getMessage());
        }
    }

    @Override
    public void exportDynamic(HttpServletResponse response) {

        List<List<String>> headList = new ArrayList<>();
        headList.add(Arrays.asList("姓名"));
        headList.add(Arrays.asList("年龄"));
        headList.add(Arrays.asList("操作时间"));


        List<List<Object>> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<Object> data = new ArrayList<>();
            data.add("张三" + i);
            data.add(20 + i);
            data.add(new Date(System.currentTimeMillis() + i));
            dataList.add(data);
        }

        String fileName = "动态表头Excel导出";
        try {
            ExcelUtil.downloadDynamic(response, fileName, headList, dataList,null);
        } catch (IOException e) {
            log.error("EasyExcel 动态表头Excel导出 失败：{}", e.getMessage());
        }

    }

    @Override
    public void exportComplex(HttpServletResponse response) {


        List<List<String>> headList = new ArrayList<>();
        headList.add(Lists.newArrayList("班级信息"));
        headList.add(Lists.newArrayList("学生信息", "姓名"));
        headList.add(Lists.newArrayList("学生信息", "年龄"));
        headList.add(Lists.newArrayList("学生信息", "入学时间"));

        List<List<Object>> dataList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            List<Object> data = new ArrayList<>();
            data.add("一年级~一班");
            data.add("张三" + i);
            data.add(20 + i);
            data.add(new Date(System.currentTimeMillis() + i));
            dataList.add(data);
        }

        String fileName = "复杂表头生成";

        try {
            ExcelUtil.downloadDynamic(response, fileName, headList, dataList,ExcelUtil.customerStyle());
        } catch (IOException e) {
            log.error("EasyExcel 复杂表头Excel导出 失败：{}", e.getMessage());
        }
    }
}
