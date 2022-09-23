package com.ws.utils;

import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author: wangshuo
 * @Date: 2022/9/23 17:40
 *
 * https://blog.csdn.net/weixin_43329956/article/details/119872924?spm=1001.2101.3001.6650.12&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-12-119872924-blog-112536203.pc_relevant_multi_platform_whitelistv6&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-12-119872924-blog-112536203.pc_relevant_multi_platform_whitelistv6&utm_relevant_index=18
 */
public class FileUtils {

    public static MultipartFile urlToFile(URL url) throws IOException {
        InputStream is = null;
        File file = null;
        FileOutputStream fos = null;
        try {
            URLConnection urlConn = null;
            urlConn = url.openConnection();
            //获取文件名
            String str = urlConn.getHeaderField("Content-Disposition");
            String[] strings = str.split("=");
            String fileName = new String(strings[1].substring(1,strings[1].length()-1).getBytes("ISO-8859-1"), "UTF-8");
            file = File.createTempFile("png", null);
            is = urlConn.getInputStream();
            fos = new FileOutputStream(file);
            byte[] buffer = new byte[4096];
            int length;
            while ((length = is.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            MultipartFile multipartFile = new MockMultipartFile(fileName, fileName,
                    ContentType.APPLICATION_OCTET_STREAM.toString(), new FileInputStream(file));
            return multipartFile;
        } catch (IOException e) {
            return null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                }
            }
        }
    }

}
