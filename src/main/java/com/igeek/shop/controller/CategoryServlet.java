package com.igeek.shop.controller;

import com.google.gson.Gson;
import com.igeek.shop.entity.Category;
import com.igeek.shop.entity.Product;
import com.igeek.shop.service.CategoryService;
import com.igeek.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @version 1.0
 * @Description 商品分类的控制层
 * @Author chenmin
 * @Date 2021/1/25 14:31
 */
@WebServlet(name = "CategoryServlet" , urlPatterns = "/category")
public class CategoryServlet extends BasicServlet {

    private CategoryService categoryService = new CategoryService();

    //查询所有商品分类
    public void viewAllCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categorys = categoryService.viewAllCategory();

        //通过json数据传递商品类别的集合
        Gson gson = new Gson();
        String json = gson.toJson(categorys);

        response.setContentType("text/html;charset=utf-8");
        //将json数据响应至客户端
        response.getWriter().write(json);
    }

}
