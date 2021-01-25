package com.igeek.shop.service;

import com.igeek.shop.dao.ProductDao;
import com.igeek.shop.entity.Product;
import com.igeek.shop.vo.PageVO;

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

    //查询商品列表
    public PageVO<Product> viewProductsByCidPname(String cid,String pname,Integer pageNow){
        PageVO<Product> vo = null;
        try {
            //获得总记录数
            int counts = productDao.selectCountsByCidPname(cid, pname);

            //计算总页数
            int myPages = (int)(counts%12==0?counts/12:Math.ceil(counts/12.0));

            //计算出起始值
            int begin = (pageNow-1)*12;

            //查询数据
            List<Product> productList = productDao.selectProductsByCidPname(cid, pname, begin);

            //封装PageVO
            vo = new PageVO<>(cid,pname,pageNow,myPages,productList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vo;
    }
}
