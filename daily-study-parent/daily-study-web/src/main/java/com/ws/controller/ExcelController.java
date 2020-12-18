package com.ws.controller;

import com.ws.excel.importor.vo.UserInfoImport;
import com.ws.excel.service.IExcelService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: wangshuo
 * @Date: 2020/12/11 17:30
 */
@Ignore
@RestController
@RequestMapping("/daily/excel")
@Slf4j
public class ExcelController {

    @Autowired
    private IExcelService excelService;


    @Ignore
    @GetMapping("/export")
    public void exportExcel(HttpServletResponse response) {
        try {
            excelService.exportExcel(response);
        } catch (Exception e) {
            log.error("excel 导出失败:" + e.getMessage());
        }
    }


    @PostMapping("/import")
    public List<UserInfoImport> importExcel(@RequestParam("file") MultipartFile file) {
        return excelService.importExcel(file);
    }
}
