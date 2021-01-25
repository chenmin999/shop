package com.igeek.shop.controller;

import com.igeek.shop.entity.User;
import com.igeek.shop.service.UserService;
import com.igeek.shop.utils.CommonUtils;
import com.igeek.shop.utils.MD5Utils;
import com.igeek.shop.utils.MailUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.mail.MessagingException;
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
        //获得请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        //{key=username,value=张三}  {key=hobby,value=[games,music]}
        //System.out.println(parameterMap);

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

        //通过MD5处理密码
        user.setPassword(MD5Utils.md5(user.getPassword()));

        //执行用户注册
        boolean flag = service.regist(user);
        if(flag){
            //注册成功，发一份邮件
            //邮件的主体内容
            String emailMsg = "恭喜您注册成功，这是一封激活邮件，请点击<a href='http://localhost:8088/user?method=active&code="+code+"'>"+code+"</a>激活";
            try {
                MailUtils.sendMail(user.getEmail(),"注册邮件激活",emailMsg);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("registSuccess.jsp").forward(request,response);
        }else{
            //注册失败
            request.getRequestDispatcher("registFail.jsp").forward(request,response);
        }
    }

    //用户激活
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        boolean flag = service.active(code);
        if(flag){
            //激活成功
            response.sendRedirect("index.jsp");
        }else{
            //激活失败
            response.sendRedirect("error.jsp");
        }
    }

    //用户昵称校验
    public void validate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        boolean flag = service.validate(username);

        //封装成json数据格式响应至客户端   json串：{"flag":flag}
        String str = "{\"flag\":"+flag+"}";
        response.getWriter().write(str);
    }

    //
}
