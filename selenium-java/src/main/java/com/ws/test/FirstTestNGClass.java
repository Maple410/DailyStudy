package com.ws.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @Author: wangshuo
 * @Date: 2021/2/25 15:59
 */
public class FirstTestNGClass {

    WebDriver driver;


    @Test
    public void openBaidu() {
        //driver.get("https://www.cnblogs.com/");
        driver.get("https://www.baidu.com/");
        driver.findElement(By.id("kw")).sendKeys("Selenium");
        driver.findElement(By.id("su")).click();
    }


    @BeforeTest
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
        //初始化浏览器名为driver
        driver = new ChromeDriver();
        //窗口最大化
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }
}
