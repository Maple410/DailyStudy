package com.ws.demo;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import sun.plugin2.message.CookieOpMessage;

/**
 * @Author: wangshuo
 * @Date: 2021/2/24 15:51
 */
public class PowerAppDemo {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "E:\\driver\\chromedriver.exe");
/*
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(Boolean.TRUE);*/


        //初始化浏览器名为driver
        WebDriver driver = new ChromeDriver();
        //窗口最大化
        driver.manage().window().maximize();
        driver.get("http://powerapp.yunzhangfang.com/view/app/1328987895368888322");

        Thread.sleep(500);
        Cookie c1 = new Cookie("access_token", "eyJhbGciOiJIUzI1NiJ9.eyJzZXQiOiIiLCJwaG9uZSI6IjEzOTEzODM1NzgwIiwiZ3NJZCI6ImRlZmF1bHQiLCJpc3MiOiJhdXRoMCIsInVzZXJOYW1lIjoic0tpU2RES3NFdmxURDFFRjdoUTFkNVFpRWlFIiwiZXhwIjoxNjE0MTU4Njc5LCJ1c2VySWQiOiI1ODIxNTQwODUifQ.YsZ-VLgz05Ar7B8IQ9IfJXCgITjnxcIIiffRZb8PZJg");
        Cookie c2 = new Cookie("refresh_token", "1b55e1a16d7f402591df10107836e60a");
        Cookie c3 = new Cookie("_userInfo", "{%22departmentId%22:%22407242578%22%2C%22tenantId%22:%2200000000%22%2C%22tenantName%22:%22%E4%BA%91%E8%B4%A6%E6%88%BF%22%2C%22userId%22:%221328653928530714626%22%2C%22userName%22:%22%E7%8E%8B%E7%A1%95%22}");
        Cookie c4 = new Cookie("Hm_lvt_92f276f442b237b8b7dec625e8840499", "1613613354,1613613399,1613815644,1614137559");
        Cookie c5 = new Cookie("Hm_lpvt_92f276f442b237b8b7dec625e8840499", "1614154558");
        //Cookie c6 = new Cookie("Cookie","refresh_token=1b55e1a16d7f402591df10107836e60a; Hm_lvt_92f276f442b237b8b7dec625e8840499=1613613354,1613613399,1613815644,1614137559; _userInfo={%22departmentId%22:%22407242578%22%2C%22tenantId%22:%2200000000%22%2C%22tenantName%22:%22%E4%BA%91%E8%B4%A6%E6%88%BF%22%2C%22userId%22:%221328653928530714626%22%2C%22userName%22:%22%E7%8E%8B%E7%A1%95%22}; Hm_lpvt_92f276f442b237b8b7dec625e8840499=1614154558; access_token=eyJhbGciOiJIUzI1NiJ9.eyJzZXQiOiIiLCJwaG9uZSI6IjEzOTEzODM1NzgwIiwiZ3NJZCI6ImRlZmF1bHQiLCJpc3MiOiJhdXRoMCIsInVzZXJOYW1lIjoic0tpU2RES3NFdmxURDFFRjdoUTFkNVFpRWlFIiwiZXhwIjoxNjE0MTU4Njc5LCJ1c2VySWQiOiI1ODIxNTQwODUifQ.YsZ-VLgz05Ar7B8IQ9IfJXCgITjnxcIIiffRZb8PZJg");
        driver.manage().addCookie(c2);
        driver.manage().addCookie(c4);
        driver.manage().addCookie(c3);
        driver.manage().addCookie(c5);
        driver.manage().addCookie(c1);
        //driver.manage().addCookie(c6);
        driver.get("http://powerapp.yunzhangfang.com/view/app/1328987895368888322");
        driver.navigate().refresh();


        //Thread.sleep(1000);
        //driver.quit();

    }
}
