package com.igeek.shop.entity;

/**
 * @version 1.0
 * @Description OrderItem  订单明细
 * @Author chenmin
 * @Date 2021/1/27 11:37
 */
public class OrderItem {
    //订单明细编号
    private String itemid;
    //商品购买的数量
    private int count;
    //商品购买的小计
    private double subtotal;

    //购买的商品  一对一关联
    private Product product;

    //所属的订单  一对一关联
    private Orders order;


    public OrderItem() {
    }

    public OrderItem(String itemid, int count, double subtotal, Product product, Orders order) {
        this.itemid = itemid;
        this.count = count;
        this.subtotal = subtotal;
        this.product = product;
        this.order = order;
    }

    /**
     * 获取
     * @return itemid
     */
    public String getItemid() {
        return itemid;
    }

    /**
     * 设置
     * @param itemid
     */
    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    /**
     * 获取
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * 设置
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 获取
     * @return subtotal
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * 设置
     * @param subtotal
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * 获取
     * @return product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * 设置
     * @param product
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * 获取
     * @return order
     */
    public Orders getOrder() {
        return order;
    }

    /**
     * 设置
     * @param order
     */
    public void setOrder(Orders order) {
        this.order = order;
    }
}
