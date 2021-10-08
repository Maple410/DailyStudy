package com.ws.general.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.service.entity.Company;

import java.util.List;

/**
 * @Author: wangshuo
 * @Date: 2021/1/4 14:23
 */
public interface ICompanyService extends IService<Company> {

    void addCompany(Company company);


    List<String> updateCompany(Company company) throws Exception;
}
