package com.igeek.shop.controller;

import com.igeek.shop.entity.Product;
import com.igeek.shop.service.CategoryService;
import com.igeek.shop.service.ProductService;
import com.igeek.shop.vo.PageVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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


    //查看商品列表，查看浏览足迹的信息
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

        /**
         * 浏览商品列表页，查看到足迹信息
         */
        List<Product> historyProductList = new ArrayList<>();
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(cookie!=null){
                    if("pids".equals(cookie.getName())){
                        String pids = cookie.getValue();  //5-10-6
                        for (String pid : pids.split("-")) {
                            Product product = productService.viewProductByPid(pid);
                            historyProductList.add(product);
                        }
                    }
                }
            }
        }

        //浏览过的商品集合存储到请求属性中
        request.setAttribute("historyProductList",historyProductList);
        //跳转页面
        request.getRequestDispatcher("product_list.jsp").forward(request,response);
    }


    //实现商品详情页展示：通过商品编号查询商品信息
    public void viewProductByPid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得请求参数
        String pid = request.getParameter("pid");
        //获得商品信息
        Product product = productService.viewProductByPid(pid);
        request.setAttribute("product",product);

        //只有从product_list.jsp页面，才能获得请求参数cid
        String cid = request.getParameter("cid");
        if(cid!=null){
            String cname = categoryService.viewCname(cid);
            request.setAttribute("cname",cname);
            request.setAttribute("cid",cid);
        }

        //获得请求参数，当前页
        String pageNow = request.getParameter("pageNow");
        request.setAttribute("pageNow",pageNow);

        //获得请求参数，搜索条件
        String pname = request.getParameter("pname");
        request.setAttribute("pname",pname);

        /**
         * 浏览商品详情页：产生足迹，将浏览过的商品信息添加至Cookie中
         */
        String value = pid;

        //若不是第一个浏览的商品，则需要从请求中获得当前Cookie的商品信息 - pids
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(cookie!=null){
                    if("pids".equals(cookie.getName())){
                        String pids = cookie.getValue();  //10-5-6
                        List<String> strings = Arrays.asList(pids.split("-"));  //[10,5,6]
                        LinkedList<String> list = new LinkedList<>(strings);  //10,5,6
                        //若之前浏览过此商品  10,5,6
                        if(list.contains(pid)){
                            //先移除
                            list.remove(pid);  //10,6
                        }
                        //将最新浏览的商品id存储至集合的第一个位置
                        list.addFirst(pid);  //5,10,6

                        //再用 - 拼接回去
                        value = "";
                        for (int i = 0; i < list.size() && i<7 ; i++) {
                            value += list.get(i) + "-";  //5-10-6-
                        }
                        value = value.substring(0,value.length()-1);  //5-10-6
                    }
                }
            }
        }

        //若是第一个浏览的商品，则直接将当前商品的id编号直接存储值Cookie
        Cookie pids = new Cookie("pids",value);
        //设置有效期
        pids.setMaxAge(7*24*60*60);
        //通过响应添加至客户端
        response.addCookie(pids);

        request.getRequestDispatcher("product_info.jsp").forward(request,response);
    }
}
