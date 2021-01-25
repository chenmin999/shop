package com.igeek.shop.vo;

import java.util.List;

/**
 * @version 1.0
 * @Description 分页辅助的类
 * @Author chenmin
 * @Date 2021/1/25 15:41
 */
public class PageVO<T> {

    //查询条件
    private String query1;
    private String query2;
    //当前页
    private Integer pageNow;
    //总页数
    private Integer myPages;
    //数据
    private List<T> list;

    public PageVO() {
    }

    public PageVO(String query1, String query2, Integer pageNow, Integer myPages, List<T> list) {
        this.query1 = query1;
        this.query2 = query2;
        this.pageNow = pageNow;
        this.myPages = myPages;
        this.list = list;
    }

    /**
     * 获取
     * @return query1
     */
    public String getQuery1() {
        return query1;
    }

    /**
     * 设置
     * @param query1
     */
    public void setQuery1(String query1) {
        this.query1 = query1;
    }

    /**
     * 获取
     * @return query2
     */
    public String getQuery2() {
        return query2;
    }

    /**
     * 设置
     * @param query2
     */
    public void setQuery2(String query2) {
        this.query2 = query2;
    }

    /**
     * 获取
     * @return pageNow
     */
    public Integer getPageNow() {
        return pageNow;
    }

    /**
     * 设置
     * @param pageNow
     */
    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }

    /**
     * 获取
     * @return myPages
     */
    public Integer getMyPages() {
        return myPages;
    }

    /**
     * 设置
     * @param myPages
     */
    public void setMyPages(Integer myPages) {
        this.myPages = myPages;
    }

    /**
     * 获取
     * @return list
     */
    public List<T> getList() {
        return list;
    }

    /**
     * 设置
     * @param list
     */
    public void setList(List<T> list) {
        this.list = list;
    }

    public String toString() {
        return "PageVO{query1 = " + query1 + ", query2 = " + query2 + ", pageNow = " + pageNow + ", myPages = " + myPages + ", list = " + list + "}";
    }
}
