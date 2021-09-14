package com.ws.controller;


import com.ws.excel.service.IEasyExcelService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/daily/easyExcel")
@Slf4j
public class EasyExcelController {

    @Autowired
    private IEasyExcelService easyExcelService;


    @PostMapping("/exportFix")
    @ApiOperation(value = "easyExcel-固定表头导出")
    public void exportFix(HttpServletResponse response) {
        easyExcelService.exportFix(response);
    }


    @PostMapping("/exportDynamic")
    @ApiOperation(value = "easyExcel-动态表头导出")
    public void exportDynamic(HttpServletResponse response) {
        easyExcelService.exportDynamic(response);
    }

    @PostMapping("/exportComplex")
    @ApiOperation(value = "easyExcel-复杂表头导出")
    public void exportComplex(HttpServletResponse response) {
        easyExcelService.exportComplex(response);
    }

}
