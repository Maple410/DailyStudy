package com.ws.demo;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @Author: wangshuo
 * @Date: 2021/2/24 16:53
 */
public class CnBlog {
    public static void main(String[] args) throws InterruptedException{
        System.setProperty ( "webdriver.chrome.driver", "E:\\driver\\chromedriver.exe" );
        //初始化浏览器名为driver
        WebDriver driver = new ChromeDriver();
        //窗口最大化
        driver.manage ().window ().maximize ();
        driver.get ( "https://home.cnblogs.com/" );
        Thread.sleep(300);
        Cookie c1 = new Cookie(".Cnblogs.AspNetCore.Cookies","CfDJ8EklyHYHyB5Oj4onWtxTnxakS_prGeDtlZlG8bbZ8ezO9EGXhCQJHSw_uv2LzUvyEMOfABYOgF0iZPQTVt4AANWnVK8sZfl9phwI8YegTf8L2s3gYEp9AtYggkJPzn1r_UcTuZCP-NLLxFXLT-vc99g-SAhhKKMwFIiWZLkBk1dPmGTLGU5SckfY1RPVd7BfiRDrkdjX4Ejbw-yT1iuaWXEMOwZohsbGV3-mRuYYJxzfKMv1snLt_Sg0tm8K0qFfdZXCapmw3zrC1xfuYxdOVTE6TLSyc3FTlqyo6vq3wGC82MfgTvh1CijNA0tifUlHlAKGOFOtNGIFex_ykVo6mbhpPMMfKqIejB7eQWhXEtlXsUpz0DJmizkCt0SltCu_FYmgZR9HhOSowzE7xP3ZXDxeutDdSeiwF3WW34bxVdz64CbQAKSprbmrIMoFdiiY2ANKK0O1RG9IKUlGrthK8Wz3dEXZreuXdWvaNe99wFT5eEb0kmu0VkPPsI9mj-77aTlVosdSKOLYU3kGaTz1lmFFTuJn1hUHGf85N7zN4bOsXWrtNrxCXaKMm90tcnp2iw");
        Cookie c2 = new Cookie(".CNBlogsCookie","3EA783CC850980DBAEB6A7E1907F99111ECE8DB36841CEA602ADF598CD9C325B8D9DF9728C811E6B60034ED3840D90FB2F769F87EA90A7C0E39F348F2C39BBB7F36883813F2769A7704B8421E1E78B633970E489");
        driver.manage().addCookie(c1);
        driver.manage().addCookie(c2);

        driver.navigate().refresh();


        //Thread.sleep(1000);
        //driver.quit();

    }

}
