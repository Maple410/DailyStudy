package com.ws.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

/**
 * @Author: wangshuo
 * @Date: 2021/2/24 17:30
 */
public class DeviceDemo {

    public static void main(String[] args) throws InterruptedException {
        //System.setProperty("webdriver.chrome.driver", "E:\\driver\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "selenium-java/src/main/resources/driver/chromedriver.exe");
        //初始化浏览器名为driver
        WebDriver driver = new ChromeDriver();
        //窗口最大化
        driver.manage().window().maximize();
        driver.get("*");
        Thread.sleep(500);

        //设置Cookie
        Cookie c1 = new Cookie("tgw_l7_route", "319ddec0229ecbb3a15f8e8e17540453");
        Cookie c2 = new Cookie("access_token", "eyJhbGciOiJIUzI1NiJ9.eyJzZXQiOiIiLCJwaG9uZSI6IjEzOTEzODM1NzgwIiwiZ3NJZCI6ImRlZmF1bHQiLCJpc3MiOiJhdXRoMCIsInVzZXJOYW1lIjoic0tpU2RES3NFdmxURDFFRjdoUTFkNVFpRWlFIiwiZXhwIjoxNjE0MTYwODEyLCJ1c2VySWQiOiI1ODIxNTQwODUifQ.oEZ6cCX_VBqXhGboSRXrw2JWGQaohz9BOZjmRwitzGo");
        Cookie c3 = new Cookie("refresh_token", "3129540eac0c424699666f08f9aee4d4");
        driver.manage().addCookie(c1);
        driver.manage().addCookie(c2);
        driver.manage().addCookie(c3);

        //针对钉钉扫码登录的 二次刷新
        driver.get("*");
        driver.navigate().refresh();

        //通过ID 对输入框进行赋值
        driver.findElement(By.id("companyName")).sendKeys("北京");

        //下拉框
        //ArrayList<WebElement> searchType = driver.findElements(By.id("36fe2cc1-dc6d-48fb-d04e-c17cdd816fab"));

        //通过class发送请求
        driver.findElement(By.cssSelector("[class='ant-btn ant-btn-primary']")).click();
        //Thread.sleep(1000);
        //driver.quit();

    }
}
