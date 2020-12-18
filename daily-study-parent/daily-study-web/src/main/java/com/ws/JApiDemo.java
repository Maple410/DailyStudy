package com.ws;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;

/**
 * @Author: wangshuo
 * @Date: 2020/12/18 15:35
 */
public class JApiDemo {


   /* 仓库地址：https://github.com/YeDaxia/JApiDocs
    中文文档：https://japidocs.agilestudio.cn/#/zh-cn/*/
    public static void main(String[] args) {
        DocsConfig config = new DocsConfig();
        config.setProjectPath("E:\\ws_study_project\\DailyStudy\\daily-study-parent\\daily-study-web");
        config.setProjectName("daily-study-web");
        config.setApiVersion("V1.0");
        config.setDocsPath("E:\\api");
        config.setAutoGenerate(Boolean.TRUE);
        Docs.buildHtmlDocs(config);
    }
}
