package com.ws.pdf.service.impl;

import com.ws.pdf.PdfUtils;
import com.ws.pdf.service.IPdfService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @Author: wangshuo
 * @Date: 2022/11/4 16:11
 */
@Service
public class PdfServiceImpl implements IPdfService {


    @Override
    public void exportPdf(HttpServletResponse response) throws Exception {
        String fileName ="离职证明";
        Map<String, String> result = new HashMap<>();
        result.put("userName", "小明");
        result.put("sex",  "男");
        result.put("idNumber", "321456581549");
        result.put("hiredate", "2022-01-01");
        result.put("resignedDate", "2022-10-10");
        result.put("position", "产品经理");
        result.put("reason", "个人原因");
        result.put("contractObject", "美团配送");
        PdfUtils.exportPdf(fileName,result,response);
    }

    @Override
    public void exportPdfZip(HttpServletResponse response) throws Exception {

        List<Map<String, String>> list = new ArrayList<>();

        for(int i=0;i<5;i++){
            Map<String, String> result = new HashMap<>();
            result.put("userName", "小明-"+i);
            result.put("sex",  "男");
            result.put("idNumber", "321456581549");
            result.put("hiredate", "2022-01-01");
            result.put("resignedDate", "2022-10-10");
            result.put("position", "产品经理");
            result.put("reason", "个人原因");
            result.put("contractObject", "美团配送");
            result.put("fileName", result.get("userName") + "-离职证明.pdf");
            list.add(result);
        }

        String fileName = "离职证明压缩包";
        String templateName = "resign.pdf";
        PdfUtils.exportPdfZip(list,fileName,templateName,response);

    }
}
