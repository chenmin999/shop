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

    /**
     * 查询符合条件的所有商品列表总记录数
     * 条件一：商品类别的编号
     * 条件二：商品名称
     */
    public int selectCountsByCidPname(String cid , String pname) throws SQLException {
        Long counts = 0L;
        //直接进行表单条件搜索
        if(cid ==null || cid.equals("")){
            String sql = "select count(*) from product where pname like concat('%',?,'%')";
            counts = (Long)this.getSingleValue(sql, pname);
        }
        //点击 商品类别 + 表单搜索
        else if(cid !=null || !cid.equals("")){
            String sql = "select count(*) from product where cid=? and pname like concat('%',?,'%')";
            counts = (Long)this.getSingleValue(sql, cid , pname);
        }
        return counts.intValue();
    }


    /**
     * 查询符合条件的所有商品列表
     * 条件一：商品类别的编号
     * 条件二：商品名称
     */
    public List<Product> selectProductsByCidPname(String cid , String pname , int begin) throws SQLException {
        List<Product> productList = null;
        //直接进行表单条件搜索
        if(cid ==null || cid.equals("")){
            String sql = "select * from product where pname like concat('%',?,'%') limit ?,12";
            productList = this.getBeanList(sql,Product.class,pname,begin);
        }
        //点击 商品类别 + 表单搜索
        else if(cid !=null || !cid.equals("")){
            String sql = "select * from product where cid=? and pname like concat('%',?,'%') limit ?,12";
            productList = this.getBeanList(sql,Product.class,cid,pname,begin);
        }
        return productList;
    }
}
