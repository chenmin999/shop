package com.igeek.shop.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(name = "BasicServlet")
public class BasicServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取执行的方法名称
        String methodName = request.getParameter("method");
        //获取子类的类类型
        Class<? extends BasicServlet> clazz = this.getClass();

        try {
            /*
             * 忽略访问权限，获取子类中的将要执行的方法对象
             * 第一个参数：将要执行的方法名称
             * 第二个和第三个参数：将要执行的方法的形参的类类型
             */
            //com.igeek.shop.controller.UserServlet.regist(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
            Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            /*
             * 执行子类对应的方法
             * 第一个参数：给哪个对象执行此方法
             * 第二个和第三个参数：执行方法时需要传递的形参
             */
            method.invoke(this, request, response);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
