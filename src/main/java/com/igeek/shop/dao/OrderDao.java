package com.igeek.shop.dao;

import com.igeek.shop.entity.OrderItem;
import com.igeek.shop.entity.Orders;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

    //通过uid用户编号，查看拥有的订单的分页列表
    public List<Orders> selectOrdersByUid(String uid,int begin) throws SQLException {
        String sql = "select * from orders where uid=? order by ordertime desc limit ?,3";
        List<Orders> orders = this.getBeanList(sql, Orders.class, uid, begin);
        return orders;
    }

    //通过uid用户编号，查看拥有的订单的总记录数
    public int selectOrdersCountsByUid(String uid) throws SQLException {
        String sql = "select count(*) from orders where uid=?";
        Long counts = (Long) this.getSingleValue(sql, uid);
        return counts.intValue();
    }

    //通过oid订单编号，查询订单明细（包含商品具体信息）
    public List<Map<String,Object>> selectOrderItemAndProduct(String oid) throws SQLException {
        String sql = "select * from orderitem i inner join product p on i.pid=p.pid where i.oid = ?";
        List<Map<String, Object>> mapList = this.getMapList(sql, oid);
        return mapList;
    }
}
