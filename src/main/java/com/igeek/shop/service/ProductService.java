package com.igeek.shop.service;

import com.igeek.shop.dao.ProductDao;
import com.igeek.shop.entity.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * @version 1.0
 * @Description TODO
 * @Author chenmin
 * @Date 2021/1/25 14:29
 */
public class ProductService {

    private ProductDao productDao = new ProductDao();

    //查询热门商品
    public List<Product> viewHot(){
        try {
            return productDao.selectAllByIsHot();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //查询最新商品
    public List<Product> viewNew(){
        try {
            return productDao.selectAllByPdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
