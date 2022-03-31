package com.ws.controller.modules;

import com.dtflys.forest.annotation.Get;
import com.ws.domains.AjaxResult;
import com.ws.modules.tran.service.ITranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangshuo
 * @Date: 2022/3/30 11:24
 */

@RestController
@RequestMapping("/api/transaction")
public class TransactionalController {


    @Autowired
    private ITranService tranService;

    /**
     * 【1】Transactional 注解标注方法修饰符为非 public 时，@Transactional 注解将会不起作用。
     *
     * @return
     */
    @GetMapping("/test1")
    public AjaxResult test1() {
        tranService.testNonPublicStatic();
        return new AjaxResult().success();
    }

    /**
     * 【2】 在类内部调用调用类内部 @Transactional 标注的方法。
     *
     * @return
     */
    @GetMapping("/test2")
    public AjaxResult test2() {
        tranService.classInside();
        return new AjaxResult().success();
    }

    /**
     * 【3】事务方法内部捕捉了异常，没有抛出新的异常，导致事务操作不会进行回滚。
     *
     * @return
     */
    @GetMapping("/test3")
    public AjaxResult test3() {
        tranService.catchException();
        return new AjaxResult().success();
    }


}
