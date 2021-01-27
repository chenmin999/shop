package com.igeek.shop.filter;

import com.igeek.shop.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//登录过滤器
@WebFilter(filterName = "f2" , urlPatterns ={"/order/*"} )
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        //获得会话对象
        HttpSession session = request.getSession();
        //从会话属性中获取登录信息
        User user = (User)session.getAttribute("user");
        //若没有登陆者user信息，则直接跳转至登录页面
        if(user==null){
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            return;
        }
        //若有登陆者user信息，则直接将过滤链传递下去
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
