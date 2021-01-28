package com.igeek.shop.service;

import com.igeek.shop.dao.OrderDao;
import com.igeek.shop.entity.Orders;
import com.igeek.shop.utils.DataSourceUtils;
import com.igeek.shop.vo.PageVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

    //更新订单中的收货人信息
    public int updateOrderUser(Orders orders){
        try {
            return dao.updateOrderUser(orders);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //修改订单状态
    public int updateOrderState(String oid){
        try {
            return dao.updateOrderState(oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //分页展示订单列表  查看我的订单
    public PageVO<Orders> viewMyOrders(String uid,int pageNow){
        //获得总记录数
        int counts = 0;
        try {
            counts = dao.selectOrdersCountsByUid(uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //计算总页数
        int myPages = (int)(counts%3==0?counts/3:Math.ceil(counts/3.0));

        //计算起始值
        int begin = (pageNow - 1)*3;

        //查询数据
        List<Orders> orders = null;
        try {
            orders = dao.selectOrdersByUid(uid, begin);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //封装PageVO
        PageVO<Orders> vo = new PageVO<>(null,null,pageNow,myPages,orders);
        return vo;
    }


    //根据订单号查询订单明细
    public List<Map<String,Object>> viewOrderItem(String oid){
        List<Map<String, Object>> mapList = null;
        try {
            mapList = dao.selectOrderItemAndProduct(oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mapList;
    }
}
