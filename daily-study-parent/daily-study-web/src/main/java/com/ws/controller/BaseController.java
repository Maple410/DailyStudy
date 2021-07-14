package com.ws.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.google.common.base.CaseFormat;
import com.ws.domains.AjaxResult;
import com.ws.domains.BasePageQuery;
import com.ws.domains.PageResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: wangshuo
 * @Date: 2021/7/9 13:49
 */
@Slf4j
public class BaseController<T extends IService, E> {

    @Autowired
    protected T baseService;


    @PostMapping("/save")
    @ApiOperation(value = "新增")
    public AjaxResult save(@RequestBody E entity) {
        baseService.save(entity);
        return new AjaxResult().success();
    }


    @PostMapping("/update")
    @ApiOperation(value = "修改")
    public AjaxResult update(@RequestBody E entity) {
        baseService.saveOrUpdate(entity);
        return new AjaxResult().success();
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "详情")
    public AjaxResult<E> getById(@PathVariable Long id) {
        return new AjaxResult<>((E) baseService.getById(id)).success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public AjaxResult deleteById(@PathVariable Long id) {
        baseService.removeById(id);
        return new AjaxResult().success();
    }


    @PostMapping("/page")
    @ApiOperation(value = "分页查询")
    public AjaxResult<PageResult<E>> page(@RequestBody BasePageQuery basePageQuery) {

        QueryWrapper<E> queryWrapper = new QueryWrapper<>();
        constructQueryParam(basePageQuery, queryWrapper);
        IPage<E> page = baseService.page(new Page<E>(basePageQuery.getPage(), basePageQuery.getPageSize(), true), queryWrapper);
        PageResult pageResult = new PageResult(page.getRecords(), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
        return new AjaxResult(pageResult).success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "列表")
    public AjaxResult<List<E>> list(@RequestBody BasePageQuery basePageQuery) {
        QueryWrapper<E> queryWrapper = new QueryWrapper<>();
        constructQueryParam(basePageQuery, queryWrapper);
        return new AjaxResult(baseService.list(queryWrapper)).success();
    }


    private void constructQueryParam(BasePageQuery basePageQuery, QueryWrapper<E> queryWrapper) {
        //查询参数
        Map<String, Object> paramMap = basePageQuery.getParams();
        for (String key : paramMap.keySet()) {
            String column = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, key);
            if (paramMap.get(key).getClass().equals(String.class)) {
                queryWrapper.like(column, paramMap.get(key));
            } else {
                queryWrapper.eq(column, paramMap.get(key));
            }
        }

        //排序
        if (StringUtils.isNoneBlank(basePageQuery.getField())) {
            String[] fieldArr = basePageQuery.getField().split(",");
            String[] orderArr = basePageQuery.getOrder().split(",");
            for (int i = 0; i < orderArr.length; i++) {
                String orderColumn = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, fieldArr[i]);
                if (orderArr[i].equals("desc")) {
                    queryWrapper.orderByDesc(orderColumn);
                } else {
                    queryWrapper.orderByAsc(orderColumn);
                }
            }
        }
    }

}
