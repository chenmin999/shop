package com.igeek.shop.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @Description Orders  订单
 * @Author chenmin
 * @Date 2021/1/27 11:37
 */
public class Orders {
    //订单编号
    private String oid;
    //下单时间
    private Date ordertime;
    //订单总金额
    private double total;
    //订单状态  0-未支付  1-已支付
    private int state;

    //当前订单的收货地址
    private String address;
    //当前订单的收货人名字
    private String name;
    //当前订单的收货人电话
    private String telephone;

    //下单者的信息  一对一关联
    private User user;

    //当前订单的订单明细  一对多关联
    private List<OrderItem> itemList = new ArrayList<>();


    public Orders() {
    }

    public Orders(String oid, Date ordertime, double total, int state, String address, String name, String telephone, User user, List<OrderItem> itemList) {
        this.oid = oid;
        this.ordertime = ordertime;
        this.total = total;
        this.state = state;
        this.address = address;
        this.name = name;
        this.telephone = telephone;
        this.user = user;
        this.itemList = itemList;
    }

    /**
     * 获取
     * @return oid
     */
    public String getOid() {
        return oid;
    }

    /**
     * 设置
     * @param oid
     */
    public void setOid(String oid) {
        this.oid = oid;
    }

    /**
     * 获取
     * @return ordertime
     */
    public Date getOrdertime() {
        return ordertime;
    }

    /**
     * 设置
     * @param ordertime
     */
    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    /**
     * 获取
     * @return total
     */
    public double getTotal() {
        return total;
    }

    /**
     * 设置
     * @param total
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * 获取
     * @return state
     */
    public int getState() {
        return state;
    }

    /**
     * 设置
     * @param state
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * 获取
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * 设置
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 获取
     * @return itemList
     */
    public List<OrderItem> getItemList() {
        return itemList;
    }

    /**
     * 设置
     * @param itemList
     */
    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    public String toString() {
        return "Orders{oid = " + oid + ", ordertime = " + ordertime + ", total = " + total + ", state = " + state + ", address = " + address + ", name = " + name + ", telephone = " + telephone + ", user = " + user + ", itemList = " + itemList + "}";
    }
}
