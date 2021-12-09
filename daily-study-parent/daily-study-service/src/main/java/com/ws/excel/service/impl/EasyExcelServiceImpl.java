package com.ws.excel.service.impl;

import com.google.common.collect.Lists;
import com.ws.excel.easyexcel.ExcelTemplateUtils;
import com.ws.excel.easyexcel.ExcelUtil;
import com.ws.excel.easyexcel.UserEntity;
import com.ws.excel.service.IEasyExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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
            ExcelUtil.downloadFix(response, fileName, UserEntity.class, dataList, null);
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
            ExcelUtil.downloadDynamic(response, fileName, headList, dataList, null);
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
            ExcelUtil.downloadDynamic(response, fileName, headList, dataList, ExcelUtil.customerStyle());
        } catch (IOException e) {
            log.error("EasyExcel 复杂表头Excel导出 失败：{}", e.getMessage());
        }
    }


    @Override
    public void exportMultiSheetTemplate(HttpServletResponse response) throws Exception {

        String fileName = "成绩确认单.xlsx";
        ClassPathResource resource = new ClassPathResource("template.xlsx");
        InputStream inputStream = resource.getInputStream();

        List<Map<String, String>> resultList = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("userName", "张三");
        map1.put("number", "20211229001");
        map1.put("class", "六年级三班");
        map1.put("cycleName", "2021年11月");
        map1.put("initialUserName", "王五");
        map1.put("initialScore", "485");
        resultList.add(map1);

        Map<String, String> map2 = new HashMap<>();
        map2.put("userName", "李四");
        map2.put("number", "20211229002");
        map2.put("class", "六年级四班");
        map2.put("cycleName", "2021年11月");
        map2.put("initialUserName", "王五");
        map2.put("initialScore", "496");
        resultList.add(map2);

        ExcelTemplateUtils.fillReportWithEasyExcel(response, resultList, fileName, inputStream);

    }
}
