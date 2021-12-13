package com.ws.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ws.domains.AjaxResult;
import com.ws.general.service.ICompanyService;
import com.ws.log.record.OperationConstant;
import com.ws.log.record.OperationLog;
import com.ws.operation.log.ChangeVo;
import com.ws.service.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: wangshuo
 * @Date: 2021/1/4 14:27
 */
@RestController
@RequestMapping("/api/company")
public class CompanyController<T extends String> {


    @Autowired
    private ICompanyService companyService;

    @GetMapping("/list")
    public AjaxResult listCompany() {
        return new AjaxResult(companyService.list(new QueryWrapper<>())).success();

    }

    @PostMapping("/add")
    @OperationLog(message = "公司信息：#{#company.name}", operation = OperationConstant.ADD)
    public AjaxResult addCompany(@RequestBody Company company) {
        companyService.save(company);
        return new AjaxResult().success();
    }

    @PostMapping("/update")
    public AjaxResult<List<ChangeVo>> updateCompany(@RequestBody Company company) throws Exception {
        return new AjaxResult<>(companyService.updateCompany(company)).success();
    }
}
