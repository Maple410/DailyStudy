package com.ws.modules.tran.service;

import com.ws.service.entity.School;

/**
 * @Author: wangshuo
 * @Date: 2024/8/12 15:14
 */
public interface ISchool1Service {


    void addRequired(School school);

    void addRequiresNew(School school);

    void addNested(School school);
}
