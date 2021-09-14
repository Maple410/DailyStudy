package com.ws.excel.service;

import javax.servlet.http.HttpServletResponse;

public interface IEasyExcelService {

    void exportFix(HttpServletResponse response);

    void exportDynamic(HttpServletResponse response);

    void exportComplex(HttpServletResponse response);
}
