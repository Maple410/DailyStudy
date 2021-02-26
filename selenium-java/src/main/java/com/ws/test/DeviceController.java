package com.ws.test;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ws.service.entity.ListDeviceInfoByCondition;
import com.ws.service.mapper.ListDeviceInfoByConditionMapper;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: wangshuo
 * @Date: 2021/2/25 13:47
 */
@RestController
@RequestMapping("/test/device")
public class DeviceController {


    @Autowired
    private ListDeviceInfoByConditionMapper conditionMapper;

    @GetMapping("list")
    public void DeviceList()throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "selenium-java/src/main/resources/driver/chromedriver.exe");
        //初始化浏览器名为driver
        WebDriver driver = new ChromeDriver();
        //窗口最大化
        driver.manage().window().maximize();
        driver.get("http://devicelog.yunzhangfang.com/deviceManage/device");

        //设置Cookie
        Cookie c1 = new Cookie("tgw_l7_route", "ecb25e2a7d1f3b505b22fa7afefaf3dc");
        Cookie c2 = new Cookie("access_token", "eyJhbGciOiJIUzI1NiJ9.eyJzZXQiOiIiLCJwaG9uZSI6IjEzOTEzODM1NzgwIiwiZ3NJZCI6ImRlZmF1bHQiLCJpc3MiOiJhdXRoMCIsInVzZXJOYW1lIjoic0tpU2RES3NFdmxURDFFRjdoUTFkNVFpRWlFIiwiZXhwIjoxNjE0MzA5MjA5LCJ1c2VySWQiOiI1ODIxNTQwODUifQ.2vKWAgYBvlVHTWyZqrgMz7F-s8J1koiCfu-d4sX0Iy8");
        Cookie c3 = new Cookie("refresh_token", "9f5d5e5de2234c298817c2cb4667317e");
        driver.manage().addCookie(c1);
        driver.manage().addCookie(c2);
        driver.manage().addCookie(c3);

        //针对钉钉扫码登录的 二次刷新
        driver.get("http://devicelog.yunzhangfang.com/deviceManage/device");
        driver.navigate().refresh();


        ListDeviceInfoByCondition condition = conditionMapper.selectById(1);
        driver.findElement(By.id("companyName")).sendKeys(condition.getCompanyName());
        //driver.findElement(By.id("machineNumber")).sendKeys(condition.getMachineNumber());
     /*   driver.findElement(By.id("phoneNo")).sendKeys(condition.getPhoneNo());
        driver.findElement(By.id("usbCompanyName")).sendKeys(condition.getUsbCompanyName());
        driver.findElement(By.id("nsrsbh")).sendKeys(condition.getNsrsbh());*/
        driver.findElement(By.cssSelector("[class='ant-btn ant-btn-primary']")).click();


        Thread.sleep(2000);
        List<WebElement> result = driver.findElements(By.cssSelector("[class='ant-table-row ant-table-row-level-0']"));
        for(int i=0;i<result.size();i++){
            System.out.println(result.get(i).getText());
            List<String> list  = Arrays.asList(result.get(i).getText().split(" "));
            System.out.println(list);
        }

    }
}
