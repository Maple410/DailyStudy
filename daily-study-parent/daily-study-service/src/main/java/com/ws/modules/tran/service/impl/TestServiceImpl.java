package com.ws.modules.tran.service.impl;

import com.ws.general.service.ISchoolService;
import com.ws.service.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: wangshuo
 * @Date: 2022/3/30 11:46
 */

@Component
public class TestServiceImpl {

    @Autowired
    private ISchoolService schoolService;


    @Transactional(rollbackFor = Exception.class)
    void nonPublicMethod() {
        boolean saveFlag = schoolService.save(new School(1, "北京", "二中"));
        if (saveFlag) {
            throw new RuntimeException();
        }
        schoolService.save(new School(2, "江西", "二中"));
    }
}
