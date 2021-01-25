package com.igeek.shop.dao;

import com.igeek.shop.entity.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * @version 1.0
 * @Description TODO
 * @Author chenmin
 * @Date 2021/1/25 14:24
 */
public class ProductDao extends BasicDao<Product> {

    //热门商品查询
    public List<Product> selectAllByIsHot() throws SQLException {
        String sql = "select * from product where is_hot=1 limit 0,?";
        List<Product> products = this.getBeanList(sql, Product.class, 9);
        return products;
    }

    //最新商品查询
    public List<Product> selectAllByPdate() throws SQLException {
        String sql = "select * from product order by pdate desc limit 0,?";
        List<Product> products = this.getBeanList(sql, Product.class, 9);
        return products;
    }
}
