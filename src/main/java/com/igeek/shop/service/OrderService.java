package com.igeek.shop.service;

import com.igeek.shop.dao.OrderDao;
import com.igeek.shop.entity.Orders;
import com.igeek.shop.utils.DataSourceUtils;

import java.sql.SQLException;

/**
 * @version 1.0
 * @Description 订单的业务逻辑类
 * @Author chenmin
 * @Date 2021/1/27 14:07
 */
public class OrderService {

    private OrderDao dao = new OrderDao();

    //提交订单
    public boolean submitOrder(Orders orders){
        try {
            //关闭默认提交事务  开启事务
            DataSourceUtils.startTransaction();

            //以下两个插入动作，要么一起完成，要么一起失败
            //插入订单
            dao.insertOrders(orders);
            //插入订单明细
            dao.insertOrderItem(orders);

            //提交订单成功
            return true;
        } catch (SQLException e) {
            e.printStackTrace();

            //回滚事务
            try {
                DataSourceUtils.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            //提交订单失败
            return false;
        }finally {
            //提交事务，并且释放资源
            try {
                DataSourceUtils.commitAndRelease();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
