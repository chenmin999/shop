package com.igeek.shop.controller;

import com.igeek.shop.entity.Cart;
import com.igeek.shop.entity.CartItem;
import com.igeek.shop.entity.Product;
import com.igeek.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @version 1.0
 * @Description 购物车的控制层
 * @Author chenmin
 * @Date 2021/1/26 15:11
 */
@WebServlet(name = "CartServlet" , urlPatterns = "/cart")
public class CartServlet extends BasicServlet{

    private ProductService productService = new ProductService();

    //加入购物车
    public void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得请求参数  商品编号
        String pid = request.getParameter("pid");

        //获得请求参数  当前购买数量
        int buyNum = Integer.parseInt(request.getParameter("buyNum"));

        //获得商品明细
        Product product = productService.viewProductByPid(pid);

        //计算当前加入商品的小计
        double subTotal = buyNum * product.getShop_price();

        //获得当前会话中的Cart对象
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");

        //若是第一次从会话中获取购物车信息
        if(cart==null){
            cart = new Cart();
        }

        double newSubTotal = subTotal;

        //获得当前会话中的购物车明细
        Map<String, CartItem> cartItemMap = cart.getMap();

        //判断此时会话中的购物车明细，是否包含正在添加的商品
        if(cartItemMap.containsKey(pid)){
            //此时购物车明细中的商品数量
            Integer oldNum = cartItemMap.get(pid).getBuyNum();
            //合并购买数量
            buyNum += oldNum;
            //重新计算，合并后的小计
            newSubTotal = buyNum * product.getShop_price();
        }

        //封装CartItem购物车明细
        CartItem cartItem = new CartItem(product,buyNum,newSubTotal);

        //添加到购物车中
        cartItemMap.put(pid,cartItem);

        //将购物车明细，添加至购物车中
        cart.setMap(cartItemMap);

        //将总金额，添加至购物车中
        double total = cart.getTotal() + subTotal;
        cart.setTotal(total);

        //再将最新的购物车信息cart，添加至会话中
        session.setAttribute("cart",cart);
        response.sendRedirect("cart.jsp");
    }

    //删除购物明细
    public void delCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得请求参数  将要删除的商品编号
        String pid = request.getParameter("pid");

        //从会话中获取购物车
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");

        //获取购物车中的购物明细
        Map<String, CartItem> cartItemMap = cart.getMap();

        //重新计算总金额 = 购物车总金额 - 将要删除的购物明细的小计
        double total = cart.getTotal() - cartItemMap.get(pid).getSubTotal();

        //将要删除的购物明细移除
        cartItemMap.remove(pid);

        //将最新情况的总金额传入
        cart.setTotal(total);

        //将购物车cart信息，传入会话属性中
        session.setAttribute("cart",cart);

        //响应重定向至cart.jsp购物车页面，避免重复提交表单
        response.sendRedirect("cart.jsp");
    }


    //清空购物车
    public void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //移除会话中的购物车信息
        session.removeAttribute("cart");
        response.sendRedirect("cart.jsp");
    }
}
