package com.ws.controller;

import com.ws.domains.AjaxResult;
import com.ws.general.service.ICompanyService;
import com.ws.service.entity.Company;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: wangshuo
 * @Date: 2020/12/11 10:39
 */

@RestController
@RequestMapping("/api/index")
public class IndexController {


    @Autowired
    private ICompanyService companyService;
    /**
     * 测试demo
     *
     * @param demo
     * @return
     */
    @GetMapping("/demo")
    @ApiOperation(value = "测试接口", notes = "路径/demo")
    public AjaxResult index(@RequestParam String demo) {
        Company company = new Company();
        company.setName(demo);
        company.setAddress(demo);
        company.setId(1);
        company.setShareholder(demo);
        companyService.addCompany(company);
        return new AjaxResult(demo).success();
    }
}
