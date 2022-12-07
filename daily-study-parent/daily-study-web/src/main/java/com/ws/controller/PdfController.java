package com.ws.controller;

import com.ws.domains.AjaxResult;
import com.ws.pdf.service.IPdfService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: wangshuo
 * @Date: 2022/11/4 16:08
 */

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    @Autowired
    private IPdfService pdfService;


    @PostMapping("/exportPdf")
    @ApiOperation(value = "按模板导出PDF文件")
    public void exportPdf(HttpServletResponse response) throws Exception{
        pdfService.exportPdf(response);
    }

    @PostMapping("/exportPdfZip")
    @ApiOperation(value = "按模板导出PDF文件压缩包")
    public void exportPdfZip(HttpServletResponse response) throws Exception{
        pdfService.exportPdfZip(response);
    }
    @GetMapping("/previewPdf")
    @ApiOperation(value = "PDF预览")
    public void previewPdf(@RequestParam("fileUrl") String fileUrl, HttpServletResponse response) throws Exception {
        pdfService.previewPdf(fileUrl, response);
    }


}
