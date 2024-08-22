package com.ws.controller;

import com.ws.modules.tran.service.ITranService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: wangshuo
 * @Date: 2024/8/9 16:02
 *
 * 参考链接：https://www.cnblogs.com/renxiuxing/p/15395798.html
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


    /**
     * Propagation.REQUIRED
     * 以下试验结果我们证明在外围方法开启事务的情况下Propagation.REQUIRED修饰的内部方法会加入到外围方法的事务中，所有Propagation.REQUIRED修饰的内部方法和外围方法均属于同一事务，只要一个方法回滚，整个事务均回滚。
     */
    @Test
    public void testRequired() {
        // 外围未开启事务 外围方法抛出异常 不回滚
        //tranService.notransaction_exception_required_required();

        // 外围未开启事务，内部方法抛出异常 抛异常的回滚
        //tranService.notransaction_required_required_exception();

        // 外围开启事务，外围方法抛出异常 全部回滚
        //tranService.transaction_exception_required_required();

        //外围开启事务,内部方法抛出异常，全部回滚
        //tranService.transaction_required_required_exception();

        //外围开启事务,内部方法抛出异常但被捕获，仍然全部回滚
        tranService.transaction_required_required_exception_try();

    }

    /**
     * Propagation.REQUIRES_NEW
     */
    @Test
    public void testRequiredNew(){

        // --通过这两个方法我们证明了在外围方法未开启事务的情况下Propagation.REQUIRES_NEW修饰的内部方法会新开启自己的事务，且开启的事务相互独立，互不干扰。

        // 外围未开启事务 外围方法抛出异常 不回滚
        //tranService.notransaction_exception_requiresNew_requiresNew();

        // 外围未开启事务，内部方法抛出异常 抛异常的回滚
        //tranService.notransaction_requiresNew_requiresNew_exception();

        // 外围开启事务，外围方法抛出异常
        //tranService.transaction_exception_required_requiresNew_requiresNew();

        //外围开启事务
        tranService.transaction_required_requiresNew_requiresNew_exception();
    }

    @Test
    public void testNested(){

        //tranService.notransaction_exception_nested_nested();
        //tranService.notransaction_nested_nested_exception();

        //tranService.transaction_exception_nested_nested();

        //tranService.transaction_nested_nested_exception();
        tranService.transaction_nested_nested_exception_try();
    }
}
