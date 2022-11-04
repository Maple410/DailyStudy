package com.ws.pdf.service;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: wangshuo
 * @Date: 2022/11/4 16:10
 */
public interface IPdfService {

    void exportPdf(HttpServletResponse response) throws Exception;

    void exportPdfZip(HttpServletResponse response) throws Exception;
}
