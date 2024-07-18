package com.ws.file;

import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * @Author: wangshuo
 * @Date: 2024/7/18 16:07
 */
public class MultipartFileUtils {

    /**
     * Base64 转 MultipartFile  一般用来转 文件上传至云服务器
     * @param base64
     * @return
     */
    public static MultipartFile base64ToMultipart(String base64) {
        try {
            String[] baseStrArr = base64.split(",");
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b;
            b = decoder.decodeBuffer(baseStrArr[1]);
            for(int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            return new Base64DecodedMultipartFile(b, baseStrArr[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
