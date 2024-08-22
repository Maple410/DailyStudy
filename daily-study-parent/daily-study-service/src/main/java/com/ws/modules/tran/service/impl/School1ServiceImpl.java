package com.ws.modules.tran.service.impl;

import com.ws.general.service.ISchoolService;
import com.ws.modules.tran.service.ISchool1Service;
import com.ws.service.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: wangshuo
 * @Date: 2024/8/12 15:14
 */
@Service
public class School1ServiceImpl implements ISchool1Service {


    @Autowired
    private ISchoolService schoolService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequired(School school) {
        schoolService.save(school);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNew(School school) {
        schoolService.save(school);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void addNested(School school) {
        schoolService.save(school);
    }
}
