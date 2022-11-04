package com.ws.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.IOUtils;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author: wangshuo
 * @Date: 2022/11/4 16:04
 */
@Slf4j
public class PdfUtils {



    public static void exportPdf(String fileName, Map<String,String> resultMap, HttpServletResponse response) throws Exception{
        // 生成导出PDF的文件名称
        fileName = URLEncoder.encode(fileName, "UTF-8");
        // 设置响应头
        response.setContentType("application/pdf;charset=UTF-8");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + fileName + ".pdf");
        OutputStream out = null;
        ByteArrayOutputStream bos = null;
        PdfStamper stamper = null;
        PdfReader reader = null;
        ClassPathResource resource = new ClassPathResource("resign.pdf");
        InputStream inputStream = resource.getInputStream();
        try {
            // 保存到本地
            // out = new FileOutputStream(fileName);
            // 输出到浏览器端
            out = response.getOutputStream();
            // 读取PDF模板表单
            reader = new PdfReader(inputStream);
            // 字节数组流，用来缓存文件流
            bos = new ByteArrayOutputStream();
            // 根据模板表单生成一个新的PDF
            stamper = new PdfStamper(reader, bos);
            // 获取新生成的PDF表单
            AcroFields form = stamper.getAcroFields();

            ClassPathResource fontSource = new ClassPathResource("simsun.ttc,0");
            // 给表单生成中文字体，这里采用系统字体，不设置的话，中文显示会有问题
            BaseFont font = BaseFont.createFont(fontSource.getFilename(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            form.addSubstitutionFont(font);
            // 装配数据

            // 遍历data，给pdf表单赋值
            for (String key : resultMap.keySet()) {
                // 图片要单独处理
                if ("studentImg".equals(key)) {
                    int pageNo = form.getFieldPositions(key).get(0).page;
                    Rectangle signRect = form.getFieldPositions(key).get(0).position;
                    float x = signRect.getLeft();
                    float y = signRect.getBottom();
                    String studentImage = resultMap.get(key).toString();
                    //根据路径或Url读取图片
                    Image image = Image.getInstance(studentImage);
                    //获取图片页面
                    PdfContentByte under = stamper.getOverContent(pageNo);
                    //图片大小自适应
                    image.scaleToFit(signRect.getWidth(), signRect.getHeight());
                    //添加图片
                    image.setAbsolutePosition(x, y);
                    under.addImage(image);
                }
                // 设置普通文本数据
                else {
                    form.setField(key, resultMap.get(key));
                }
            }
            // 表明该PDF不可修改
            stamper.setFormFlattening(true);
            // 关闭资源
            stamper.close();
            // 将ByteArray字节数组中的流输出到out中（即输出到浏览器）
            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();
        } catch (Exception e) {
            log.error("离职证明导出失败：{}", e);
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void exportPdfZip(List<Map<String, String>> list, String zipName, String templateName, HttpServletResponse response) throws Exception {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        for (Map<String, String> map : list) {
            ByteArrayOutputStream bos = null;
            PdfStamper stamper = null;
            PdfReader reader = null;
            ClassPathResource resource = new ClassPathResource(templateName);
            InputStream inputStream = resource.getInputStream();
            reader = new PdfReader(inputStream);
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            ClassPathResource fontSource = new ClassPathResource("simsun.ttc,0");
            BaseFont font = BaseFont.createFont(fontSource.getFilename(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            form.addSubstitutionFont(font);
            for (String key : map.keySet()) {
                form.setField(key, map.get(key));
            }
            stamper.setFormFlattening(true);
            stamper.close();

            zip.putNextEntry(new ZipEntry(map.get("fileName")));
            byte[] bytes = bos.toByteArray();
            zip.write(bytes, 0, bytes.length);
            zip.flush();
            zip.closeEntry();
        }


        IOUtils.closeQuietly(zip);
        zipName = URLEncoder.encode(zipName, "UTF-8");
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=" + zipName);
        response.addHeader("Content-Length", "" + outputStream.toByteArray().length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.copy(new ByteArrayInputStream(outputStream.toByteArray()), response.getOutputStream());
    }

}
