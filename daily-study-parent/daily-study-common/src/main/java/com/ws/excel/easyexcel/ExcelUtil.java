package com.ws.excel.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 参考 ：https://zhuanlan.zhihu.com/p/153439500  http://www.justdojava.com/2021/04/18/easyexcel-01/
 */
public class ExcelUtil {

    /**
     * 实体类注解方式生成文件
     *
     * @param response
     * @param fileName
     * @param cls
     * @param dataList
     * @throws IOException
     */
    public static void downloadFix(HttpServletResponse response, String fileName, Class cls, List dataList, HorizontalCellStyleStrategy cellStyleStrategy) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fname = URLEncoder.encode(fileName, "utf-8");
        response.setHeader("Content-disposition",
                "attachment;filename=" + fname + ExcelTypeEnum.XLSX.getValue());

        LongestMatchColumnWidthStyleStrategy longestMatchColumnWidthStyleStrategy =
                new LongestMatchColumnWidthStyleStrategy();
        EasyExcel.write(response.getOutputStream(), cls)
                .registerWriteHandler(cellStyleStrategy)
                .sheet("sheet1")
                .registerWriteHandler(longestMatchColumnWidthStyleStrategy)
                .doWrite(dataList);
        response.flushBuffer();
    }


    public static void downloadDynamic(HttpServletResponse response, String fileName, List<List<String>> headList, List<List<Object>> dataList,HorizontalCellStyleStrategy cellStyleStrategy) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fname = URLEncoder.encode(fileName, "utf-8");
        response.setHeader("Content-disposition",
                "attachment;filename=" + fname + ExcelTypeEnum.XLSX.getValue());

        LongestMatchColumnWidthStyleStrategy longestMatchColumnWidthStyleStrategy =
                new LongestMatchColumnWidthStyleStrategy();
        EasyExcel.write(response.getOutputStream())
                .registerWriteHandler(cellStyleStrategy)
                .head(headList).sheet("sheet1")
                .doWrite(dataList);
    }

    /**
     * 自定义样式
     *
     * @return
     */
    public static HorizontalCellStyleStrategy customerStyle() {
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为红色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 20);
        headWriteCellStyle.setWriteFont(headWriteFont);
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // 背景绿色
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short) 20);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        return horizontalCellStyleStrategy;
    }
}
