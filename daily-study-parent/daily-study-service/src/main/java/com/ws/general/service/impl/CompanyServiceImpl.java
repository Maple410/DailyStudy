package com.ws.general.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ws.general.service.ICompanyService;
import com.ws.service.entity.Company;
import com.ws.service.mapper.CompanyMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: wangshuo
 * @Date: 2021/1/4 14:25
 */
@Service
public class CompanyServiceImpl  extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {
}
