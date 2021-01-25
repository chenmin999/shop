package com.igeek.shop.dao;

import com.igeek.shop.entity.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * @version 1.0
 * @Description 商品类别的数据处理
 * @Author chenmin
 * @Date 2021/1/25 15:10
 */
public class CategoryDao extends BasicDao<Category> {

    //查询所有的商品类别
    public List<Category> selectAllCategory() throws SQLException {
        return this.getBeanList("select * from category",Category.class);
    }

}
