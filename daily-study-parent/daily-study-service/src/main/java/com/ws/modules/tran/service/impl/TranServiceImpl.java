package com.ws.modules.tran.service.impl;


import com.ws.general.service.ISchoolService;
import com.ws.modules.tran.service.ISchool1Service;
import com.ws.modules.tran.service.ISchool2Service;
import com.ws.modules.tran.service.ITranService;
import com.ws.service.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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


    @Autowired
    private ISchool1Service school1Service;

    @Autowired
    private ISchool2Service school2Service;

    /**
     * 【1】Transactional 注解标注方法修饰符为非 public 时，@Transactional 注解将会不起作用。
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

    /**
     * 可以将异常捕获然后手动回滚
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void manualRollBack() {
        try {
            boolean saveFlag = schoolService.save(new School(1, "手动回滚", "手动回滚"));
            if (saveFlag) {
                throw new RuntimeException();
            }
            schoolService.save(new School(2, "手动回滚1", "手动回滚1"));
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Transactional
    public void testClassInside() {
        boolean saveFlag = schoolService.save(new School(1, "北京", "二中"));
        if (saveFlag) {
            throw new RuntimeException();
        }
        schoolService.save(new School(2, "江西", "二中"));
    }


    /**
     * 外围方法未开启事务， 两个插入方法在各自的事务中独立运行，外围方法异常不影响内部插入方法 ，因此不会回滚
     */
    @Override
    public void notransaction_exception_required_required() {

        School school1 = new School(1, "北京", "家里蹲大学");
        school1Service.addRequired(school1);

        School school2 = new School(2, "南京", "家里蹲大学");
        school2Service.addRequired(school2);

        throw new RuntimeException();

    }

    // -----------------  Propagation.REQUIRED ----------------

    /**
     * 外围方法未开启事务，两个插入方法在各自的事务中独立运行
     * 第二个方法内发生异常只回滚该方法；
     * 第一个方法不受影响，数据正常插入；
     */
    @Override
    public void notransaction_required_required_exception() {

        School school1 = new School(1, "北京", "家里蹲大学");
        school1Service.addRequired(school1);

        School school2 = new School(2, "南京", "家里蹲大学");
        school2Service.addRequiredException(school2);
    }

    /**
     * 外围方法开启事务，内部方法加入外围方法事务，外围方法回滚，内部方法也要回滚。
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_required_required() {

        School school1 = new School(1, "北京", "家里蹲大学");
        school1Service.addRequired(school1);

        School school2 = new School(2, "南京", "家里蹲大学");
        school2Service.addRequired(school2);

        throw new RuntimeException();

    }

    /**
     * 外围方法开启事务，内部方法加入外围方法事务，内部方法抛出异常回滚，外围方法感知异常致使整体事务回滚。
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_required_exception() {
        School school1 = new School(1, "北京", "家里蹲大学");
        school1Service.addRequired(school1);

        School school2 = new School(2, "南京", "家里蹲大学");
        school2Service.addRequiredException(school2);
    }

    /**
     * 外围方法开启事务，内部方法加入外围方法事务，内部方法抛出异常回滚，即使方法被catch不被外围方法感知，整个事务依然回滚
     */
    @Override
    //@Transactional(propagation = Propagation.REQUIRED)
    @Transactional
    public void transaction_required_required_exception_try() {

        School school1 = new School(1, "北京", "家里蹲大学");
        school1Service.addRequired(school1);

        School school2 = new School(2, "南京", "家里蹲大学");
        try {
            school2Service.addRequiredException(school2);
        } catch (Exception e) {
            System.out.println("方法回滚");
        }
    }


    // -----------------  Propagation.REQUIRES_NEW ----------------

    /**
     * 外围方法未开启事务， 两个插入方法在各自的事务中独立运行，外围方法异常不影响内部插入方法 ，因此不会回滚
     */
    @Override
    public void notransaction_exception_requiresNew_requiresNew() {

        School school1 = new School(1, "北京", "家里蹲大学");
        school1Service.addRequiresNew(school1);

        School school2 = new School(2, "南京", "家里蹲大学");
        school2Service.addRequiresNew(school2);

        throw new RuntimeException();
    }


    /**
     * 外围方法未开启事务，两个插入方法在各自的事务中独立运行
     * 第二个方法内发生异常只回滚该方法；
     * 第一个方法不受影响，数据正常插入；
     */
    @Override
    public void notransaction_requiresNew_requiresNew_exception() {

        School school1 = new School(1, "北京", "家里蹲大学");
        school1Service.addRequiresNew(school1);

        School school2 = new School(2, "南京", "家里蹲大学");
        school2Service.addRequiresNewException(school2);
    }

    /**
     * 外围方法开启事务
     * 第一个方法和外围方法一个事务 回滚
     * 另外两个方法分别在独立的事务中都不受影响，数据正常插入
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_required_requiresNew_requiresNew() {

        School school1 = new School(1, "北京", "家里蹲大学");
        school1Service.addRequired(school1);

        School school2 = new School(2, "南京", "家里蹲大学");
        school2Service.addRequiresNew(school2);

        School school3 = new School(3, "天津", "家里蹲大学");
        school2Service.addRequiresNew(school3);

        throw new RuntimeException();
    }

    /**
     * 外围方法开启事务
     * 第一个方法和外围方法一个事务 回滚
     * 另外两个方法分别在独立的事务中
     * 第三个方法抛出异常回滚，异常被外围方法感知，外围事务亦被回滚即第一个方法回滚
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_requiresNew_requiresNew_exception() {
        School school1 = new School(1, "北京", "家里蹲大学");
        school1Service.addRequired(school1);

        School school2 = new School(2, "南京", "家里蹲大学");
        school2Service.addRequiresNew(school2);

        School school3 = new School(3, "天津", "家里蹲大学");
        school2Service.addRequiresNewException(school3);
    }

    /**
     * 外围方法开启事务
     * 第一个方法和外围方法一个事务
     * 另外两个方法分别在独立的事务中
     * 第三个方法抛出异常回滚，异常不会被外围方法感知，外围方法事务不回滚
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_requiresNew_requiresNew_exception_try() {

        School school1 = new School(1, "北京", "家里蹲大学");
        school1Service.addRequired(school1);

        School school2 = new School(2, "南京", "家里蹲大学");
        school2Service.addRequiresNew(school2);

        School school3 = new School(3, "天津", "家里蹲大学");
        try {
            school2Service.addRequiresNewException(school3);
        } catch (Exception e) {
            System.out.println("回滚");
        }
    }

    // -----------------  Propagation.NESTED ----------------
    // 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作。


    // 在外围方法未开启事务的情况下Propagation.NESTED和Propagation.REQUIRED作用相同，修饰的内部方法都会新开启自己的事务，且开启的事务相互独立，互不干扰。
    /**
     * 外围方法未开启事务， 两个插入方法在各自的事务中独立运行，外围方法异常不影响内部插入方法 ，因此不会回滚
     */
    @Override
    public void notransaction_exception_nested_nested() {

        School school1 = new School(1, "北京", "家里蹲大学");
        school1Service.addNested(school1);

        School school2 = new School(2, "南京", "家里蹲大学");
        school2Service.addNested(school2);

        throw new RuntimeException();

    }

    /**
     * 外围方法未开启事务，两个插入方法在各自的事务中独立运行
     * 第二个方法内发生异常只回滚该方法；
     * 第一个方法不受影响，数据正常插入；
     */
    @Override
    public void notransaction_nested_nested_exception() {

        School school1 = new School(1, "北京", "家里蹲大学");
        school1Service.addNested(school1);

        School school2 = new School(2, "南京", "家里蹲大学");
        school2Service.addNestedException(school2);
    }


    // 在外围方法开启事务的情况下Propagation.NESTED修饰的内部方法属于外部事务的子事务，外围主事务回滚，子事务一定回滚，而内部子事务可以单独回滚而不影响外围主事务和其他子事务
    /**
     * 外围方法开启事务，内部事务为外围事务的子事务，外围方法回滚，内部方法也要回滚。
     */
    @Override
    @Transactional
    public void transaction_exception_nested_nested() {

        School school1 = new School(1, "北京", "家里蹲大学");
        school1Service.addNested(school1);

        School school2 = new School(2, "南京", "家里蹲大学");
        school2Service.addNested(school2);

        throw new RuntimeException();
    }


    /**
     * 外围方法开启事务，内部事务为外围事务的子事务，内部方法抛出异常回滚，且外围方法感知异常致使整体事务回滚。
     */
    @Override
    @Transactional
    public void transaction_nested_nested_exception() {
        School school1 = new School(1, "北京", "家里蹲大学");
        school1Service.addNested(school1);

        School school2 = new School(2, "南京", "家里蹲大学");
        school2Service.addNestedException(school2);
    }

    @Override
    @Transactional
    public void transaction_nested_nested_exception_try() {
        School school1 = new School(1, "北京", "家里蹲大学");
        school1Service.addNested(school1);

        School school2 = new School(2, "南京", "家里蹲大学");
        try {
            school2Service.addNestedException(school2);
        } catch (Exception e) {
            System.out.println("方法回滚");
        }
    }
}
