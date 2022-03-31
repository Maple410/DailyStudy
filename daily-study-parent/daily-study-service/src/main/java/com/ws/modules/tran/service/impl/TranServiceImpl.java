package com.ws.modules.tran.service.impl;


import com.ws.general.service.ISchoolService;
import com.ws.modules.tran.service.ITranService;
import com.ws.service.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: wangshuo
 * @Date: 2022/3/30 11:26
 */
@Service
public class TranServiceImpl implements ITranService {

    @Autowired
    private TestServiceImpl testService;

    @Autowired
    private ISchoolService schoolService;

    /**
     *  【1】Transactional 注解标注方法修饰符为非 public 时，@Transactional 注解将会不起作用。
     */
    @Override
    public void testNonPublicStatic() {
        testService.nonPublicMethod();
    }


    /**
     * 【2】 在类内部调用调用类内部 @Transactional 标注的方法。
     */
    @Override
    public void classInside() {
        this.testClassInside();
    }


    /**
     * 【3】事务方法内部捕捉了异常，没有抛出新的异常，导致事务操作不会进行回滚。
     */
    @Override
    @Transactional
    public void catchException() {
        try {
            boolean saveFlag = schoolService.save(new School(1, "北京", "二中"));
            if (saveFlag) {
                throw new RuntimeException();
            }
            schoolService.save(new School(2, "江西", "二中"));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void testClassInside(){
        boolean saveFlag = schoolService.save(new School(1, "北京", "二中"));
        if (saveFlag) {
            throw new RuntimeException();
        }
        schoolService.save(new School(2, "江西", "二中"));
    }
}
