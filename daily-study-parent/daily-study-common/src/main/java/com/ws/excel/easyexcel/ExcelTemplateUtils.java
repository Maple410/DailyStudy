package com.ws.excel.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangshuo
 * @Date: 2021/12/9 10:16
 * 参考资料
 * https://www.cnblogs.com/Donnnnnn/p/15412128.html
 * https://blog.csdn.net/weixin_43849468/article/details/116206454
 */
public class ExcelTemplateUtils {


    /**
     * 模板多sheet导出 比如批量导出 成绩确认单
     * @param response
     * @param resultList
     * @param fileName
     * @param inputStream
     */

    public static void fillReportWithEasyExcel(HttpServletResponse response, List<Map<String, String>> resultList, String fileName, InputStream inputStream) {
        ExcelWriter excelWriter = null;
        try {
            OutputStream outputStream = response.getOutputStream();
            String headStr = "attachment; filename=\"" + fileName + "\"";
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", headStr);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            //设置模板的第一个sheet的名称
            workbook.setSheetName(0, resultList.get(0).get("userName"));
            for (int i = 1; i < resultList.size(); i++) {
                //复制模板，得到第i个sheet
                Map<String, String> map = resultList.get(i);
                workbook.cloneSheet(0, map.get("userName"));
            }
            //写到流里
            workbook.write(bos);
            byte[] bArray = bos.toByteArray();
            InputStream is = new ByteArrayInputStream(bArray);

            excelWriter = EasyExcel.write(outputStream).withTemplate(is).build();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> map = resultList.get(i);
                WriteSheet writeSheet = EasyExcel.writerSheet(i).build();
                FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
                excelWriter.fill(null, fillConfig, writeSheet);
                excelWriter.fill(map, writeSheet);//这里只需要map填充
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excelWriter.finish();
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
