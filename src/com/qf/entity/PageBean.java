package com.qf.entity;

import java.util.List;

//PageBean<T>加一个泛型T
public class PageBean<T> {

    private List<T> list;//展示的数据
    private int currentPage;//当前页数
    private int pageSize;//页容量，每页显示的数据条数
    private long totalCount; //总条数
    private int totalPage; //总页数

    //不传入总页数（构造方法）
    public PageBean(List<T> list, int currentPage, int pageSize, long totalCount) {
        this.list = list;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    //添加get/set方法
    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        //需要进行一个运算
        //13条数据  每页显示5   3（Math.ceil向下舍余）（totalCount*1.0转成double类型）
        return (int) Math.ceil(totalCount*1.0/pageSize);
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
