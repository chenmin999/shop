package com.igeek.shop.controller;

import com.igeek.shop.entity.*;
import com.igeek.shop.service.OrderService;
import com.igeek.shop.utils.CommonUtils;
import com.igeek.shop.vo.PageVO;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Description 订单的控制类
 * @Author chenmin
 * @Date 2021/1/27 14:22
 */
@WebServlet(name = "OrderServlet" , urlPatterns = "/order")
public class OrderServlet extends BasicServlet{

    private OrderService service = new OrderService();

    //提交订单
    public void submitOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //大前提   当前已经是登录状态
        HttpSession session = request.getSession();

        //创建订单
        Orders orders = new Orders();

        //随机产生订单编号
        orders.setOid(CommonUtils.getUUID().replaceAll("-",""));
        //设置下单时间
        orders.setOrdertime(new Date());
        //设置订单状态 0-未支付 1-已支付
        orders.setState(0);

        //从会话中获得登陆者信息，来设置下单者
        User user = (User) session.getAttribute("user");
        orders.setUser(user);

        //从会话中获取购物车信息
        Cart cart = (Cart) session.getAttribute("cart");

        //设置订单总金额
        orders.setTotal(cart.getTotal());

        //设置订单明细
        Map<String, CartItem> cartMap = cart.getMap();
        for (Map.Entry<String,CartItem> entry : cartMap.entrySet()) {
            //获得购物车明细
            CartItem cartItem = entry.getValue();

            //创建订单明细
            OrderItem orderItem = new OrderItem();

            //设置订单明细编号
            orderItem.setItemid(CommonUtils.getUUID().replaceAll("-",""));
            //设置商品购买数量
            orderItem.setCount(cartItem.getBuyNum());
            //设置商品购买的小计
            orderItem.setSubtotal(cartItem.getSubTotal());
            //设置购买的商品信息
            orderItem.setProduct(cartItem.getProduct());
            //设置所属的订单
            orderItem.setOrder(orders);

            //设置订单中的多个订单明细
            orders.getItemList().add(orderItem);
        }

        boolean flag = service.submitOrder(orders);
        if(flag){
            //提交订单成功
            session.setAttribute("orders",orders);
            response.sendRedirect("order_info.jsp");
        }else{
            //提交订单失败
            request.setAttribute("msg","当前订单提交失败，请尝试重新提交");
            request.getRequestDispatcher("cart.jsp").forward(request,response);
        }
    }

    //确认订单
    public void confirmOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.收集表单中的数据
        Map<String, String[]> map = request.getParameterMap();

        //2.从会话中获取当前订单信息
        HttpSession session = request.getSession();
        Orders orders = (Orders)session.getAttribute("orders");

        //3.更新订单中收货人信息
        int i = 0;
        try {
            BeanUtils.populate(orders,map);
            i = service.updateOrderUser(orders);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //4.若更新成功，则跳转至支付页面
        if(i>0){
            response.sendRedirect(request.getContextPath()+"/alipay.trade.page.pay.jsp");
        }
    }


    //查看我的订单
    public void viewMyOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得请求参数  当前页
        String page = request.getParameter("pageNow");
        //默认查询第一页
        int pageNow = 1;
        if(page!=null){
            pageNow = Integer.parseInt(page);
        }

        //从当前会话中获取登录信息
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user!=null){
            PageVO<Orders> vo = service.viewMyOrders(user.getUid(), pageNow);
            //查看我的订单列表
            List<Orders> ordersList = vo.getList();

            //查看我的订单中的明细
            if(ordersList!=null){
                for (Orders orders : ordersList) {

                    //查询订单明细
                    List<Map<String, Object>> orderItemAndProductList = service.viewOrderItem(orders.getOid());

                    for (Map<String, Object> map : orderItemAndProductList) {
                        //订单明细
                        OrderItem orderItem = new OrderItem();
                        //商品
                        Product product = new Product();

                        //装配对象中的各个属性值
                        try {
                            BeanUtils.populate(orderItem,map);
                            BeanUtils.populate(product,map);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }

                        //将商品信息装配至订单明细
                        orderItem.setProduct(product);
                        //将订单明细装配至订单
                        orders.getItemList().add(orderItem);
                    }
                }
            }
            request.setAttribute("vo",vo);
        }

        request.getRequestDispatcher("order_list.jsp").forward(request,response);
    }
}
