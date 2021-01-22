package com.igeek.shop.controller;

import com.igeek.shop.entity.User;
import com.igeek.shop.service.UserService;
import com.igeek.shop.utils.CommonUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet(name = "UserServlet" , urlPatterns = "/user")
public class UserServlet extends BasicServlet {

    private UserService service = new UserService();

    //用户注册
    public void regist(final HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        //{key=username,value=张三}  {key=hobby,value=[games,music]}
        System.out.println(parameterMap);

        User user = new User();

        /**
         * 注册转换器，目标 String ->  Date
         * 第一个参数：定义转换规则
         * 第二个参数：将String转换成哪个目标类型
         */
        ConvertUtils.register(new Converter() {
            @Override
            public Object convert(Class clazz, Object o) {
                Date birthday = null;
                if(o instanceof String){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        birthday = sdf.parse(request.getParameter("birthday"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                return birthday;
            }
        }, Date.class);

        //通过BeanUtils工具类，给user对象赋值属性
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //给user对象设置uid
        user.setUid(CommonUtils.getUUID().replaceAll("-",""));

        //给user对象设置激活码code
        String code = CommonUtils.getUUID().replaceAll("-","");
        user.setCode(code);

        //执行用户注册
        boolean flag = service.regist(user);
        if(flag){
            //注册成功
            request.getRequestDispatcher("registSuccess.jsp").forward(request,response);
        }else{
            //注册失败
            request.getRequestDispatcher("registFail.jsp").forward(request,response);
        }
    }

    //用户激活

    //用户登录

    //
}
