package com.igeek.shop.controller;

import com.igeek.shop.entity.Product;
import com.igeek.shop.service.CategoryService;
import com.igeek.shop.service.ProductService;
import com.igeek.shop.vo.PageVO;

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
    private CategoryService categoryService = new CategoryService();

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


    //查看商品列表
    public void viewProductListByCidPname(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从请求参数中获取当前页
        String page = request.getParameter("pageNow");
        int pageNow = 1;   //默认查询第一页
        if(page!=null){
            pageNow = Integer.parseInt(page);
        }

        //从请求参数中获取查询条件 cid  pname
        String cid = request.getParameter("cid");
        if(cid!=null && !"".equals(cid)){
            String cname = categoryService.viewCname(cid);
            request.setAttribute("cname",cname);
        }

        String pname = request.getParameter("pname");
        if(pname==null){
            pname = "";  //默认查询所有商品信息
        }

        //查询所有的商品信息
        PageVO<Product> vo = productService.viewProductsByCidPname(cid, pname, pageNow);
        request.setAttribute("vo",vo);

        //跳转页面
        request.getRequestDispatcher("product_list.jsp").forward(request,response);
    }
}
