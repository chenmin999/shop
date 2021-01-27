package com.igeek.shop.dao;

import com.igeek.shop.entity.OrderItem;
import com.igeek.shop.entity.Orders;

import java.sql.SQLException;
import java.util.List;

/**
 * @version 1.0
 * @Description 订单的数据交互类
 * @Author chenmin
 * @Date 2021/1/27 14:07
 */
public class OrderDao extends BasicDao<Orders> {

    //插入订单
    public int insertOrders(Orders orders) throws SQLException {
        String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
        int i = this.update(sql, orders.getOid(), orders.getOrdertime(), orders.getTotal(),
                orders.getState(), orders.getAddress(), orders.getName(),
                orders.getTelephone(), orders.getUser().getUid());
        return i;
    }

    //插入订单明细
    public int insertOrderItem(Orders orders) throws SQLException {
        int i = 0;
        String sql = "insert into orderitem values(?,?,?,?,?)";
        //获取当前订单中的订单明细
        List<OrderItem> itemList = orders.getItemList();
        for (OrderItem item : itemList) {
            i = this.update(sql , item.getItemid() , item.getCount() ,
                    item.getSubtotal(),item.getProduct().getPid(),item.getOrder().getOid());
        }
        return i;
    }

    //更新订单中的收货人信息
    public int updateOrderUser(Orders orders) throws SQLException {
        String sql = "update orders set name=? , address=? , telephone=? where oid=?";
        int i = this.update(sql, orders.getName(), orders.getAddress(), orders.getTelephone(), orders.getOid());
        return i;
    }

    //更新订单状态
    public int updateOrderState(String oid) throws SQLException {
        String sql = "update orders set state = 1 where oid = ?";
        int i = this.update(sql,oid);
        return i;
    }
}
