package com.ws.modules.tran.service;

import com.ws.service.entity.School;

/**
 * @Author: wangshuo
 * @Date: 2024/8/12 15:14
 */
public interface ISchool2Service {


    void addRequired(School school);

    void addRequiredException(School school);

    void addRequiresNew(School school);

    void addRequiresNewException(School school);

    void addNested(School school);

    void addNestedException(School school);

}
