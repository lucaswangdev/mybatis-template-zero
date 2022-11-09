package com.lucaswangdev.entity;

public class User extends UserBase{
    // 分页-页数
    private Integer pageNo;

    // 分页-每页条数
    private Integer pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}