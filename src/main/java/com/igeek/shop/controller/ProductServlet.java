package com.igeek.shop.controller;

import com.igeek.shop.entity.Product;
import com.igeek.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @version 1.0
 * @Description 商品模块的控制层
 * @Author chenmin
 * @Date 2021/1/25 14:31
 */
@WebServlet(name = "ProductServlet" , urlPatterns = "/product")
public class ProductServlet extends BasicServlet {

    private ProductService productService = new ProductService();

    //展示首页
    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询热门商品
        List<Product> productsHot = productService.viewHot();
        request.setAttribute("productsHot",productsHot);

        //查询最新商品
        List<Product> productsNew = productService.viewNew();
        request.setAttribute("productsNew",productsNew);

        //跳转至首页
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

}
