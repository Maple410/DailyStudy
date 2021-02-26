package com.ws.query;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: wangshuo
 * @Date: 2021/2/26 15:20
 */
@Getter
@Setter
public class PageResult<T> implements Serializable {


    private int totalCount;

    private int totalPage;

    private int currentPage;

    private int pageSize;

    private List<T> list;

    public PageResult() {
    }

    public PageResult(int totalCount, int currentPage, int pageSize, List<T> list) {
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.list = list;
        this.totalPage = (int)Math.ceil((double)totalCount / (double)pageSize);
    }
}
