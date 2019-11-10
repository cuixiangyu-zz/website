package com.cxy.website.model;

import java.util.List;

/**
 * @Title: PageDataResult
 * @Description: 封装DTO分页数据（记录数和所有记录）
 * @author: haha
 * @version: 1.0
 * @date: 2019/08/03 15:17
 */
public class PageDataResult {



    //总记录数量
    private Integer totals;

    private List<?> list;


    public Integer getTotals() {
        return totals;
    }

    public void setTotals(Integer totals) {
        this.totals = totals;
    }

    public List <?> getList() {
        return list;
    }

    public void setList(List <?> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageDataResult{" +
                ", totals=" + totals +
                ", list=" + list +
                '}';
    }
}
