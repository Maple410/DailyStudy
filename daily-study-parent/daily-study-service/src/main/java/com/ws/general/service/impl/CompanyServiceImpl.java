package com.ws.general.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.eventbus.AsyncEventBus;
import com.ws.general.service.ICompanyService;
import com.ws.service.entity.Company;
import com.ws.service.mapper.CompanyMapper;
import com.ws.test.TestCompare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wangshuo
 * @Date: 2021/1/4 14:25
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {

    @Autowired
    private AsyncEventBus asyncEventBus;

    @Override
    public void addCompany(Company company) {

        asyncEventBus.post(company.getName());

    }

    @Override
    public List<String> updateCompany(Company company) throws Exception {
        Company historyObject = this.getById(company.getId());
        this.updateById(company);
        return TestCompare.compareDifference(historyObject, company);
    }
}
