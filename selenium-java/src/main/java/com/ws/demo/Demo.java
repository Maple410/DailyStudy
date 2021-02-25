package com.ws.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @Author: wangshuo
 * @Date: 2021/2/24 15:08
 *
 * 参考链接 网上搜一搜 https://blog.csdn.net/u011541946/category_6958269.html
 */
public class Demo {
    public static void main(String[] args) {
        //指定浏览器驱动路径
       // System.setProperty ( "webdriver.chrome.driver", "C:\\Users\\lenovo\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe" );
        System.setProperty ( "webdriver.chrome.driver", "E:\\driver\\chromedriver.exe" );
        //初始化浏览器名为driver
        WebDriver driver = new ChromeDriver();
        //窗口最大化
        driver.manage ().window ().maximize ();
        //使用get()方法，打开百度网址
        driver.get ( "http://www.baidu.com" );

        try {
            String baidu_title = "百度一下，你就知道";
            System.out.println(driver.getTitle());
            assert baidu_title == driver.getTitle();
            System.out.println("Test pass");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //关闭并退出浏览器
       // driver.quit ();
    }

}
