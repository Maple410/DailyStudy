package com.ws.controller;

import com.ws.modules.tran.service.ITranService;
import org.hibernate.validator.constraints.SafeHtml;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: wangshuo
 * @Date: 2024/8/9 16:02
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TransactionalTestController {

    @Autowired
    private ITranService tranService;

    @Test
    public void test() {
        tranService.manualRollBack();
    }
}
