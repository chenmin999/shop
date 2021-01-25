package com.igeek.shop.service;

import com.igeek.shop.dao.CategoryDao;
import com.igeek.shop.entity.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * @version 1.0
 * @Description TODO
 * @Author chenmin
 * @Date 2021/1/25 15:12
 */
public class CategoryService {

    private CategoryDao dao = new CategoryDao();

    //查询所有商品分类
    public List<Category> viewAllCategory(){
        try {
            return dao.selectAllCategory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
