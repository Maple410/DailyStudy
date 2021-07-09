package com.ws.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ws.domains.AjaxResult;
import com.ws.general.service.ICompanyService;
import com.ws.service.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: wangshuo
 * @Date: 2021/1/4 14:27
 */
@RestController
@RequestMapping("/api/company")
public class CompanyController {


    @Autowired
    private ICompanyService companyService;

    @GetMapping("/list")
    public AjaxResult listCompany() {
        return new AjaxResult(companyService.list(new QueryWrapper<>())).success();

    }

    @PostMapping("/add")
    public AjaxResult addCompany(@RequestBody Company company) {
        companyService.save(company);
        return new AjaxResult().success();
    }
}
