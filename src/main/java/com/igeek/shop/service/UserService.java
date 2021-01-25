package com.igeek.shop.service;

import com.igeek.shop.dao.UserDao;
import com.igeek.shop.entity.User;
import com.igeek.shop.utils.DataSourceUtils;

import java.sql.SQLException;

/**
 * @version 1.0
 * @Description 用户模块的业务逻辑类
 * @Author chenmin
 * @Date 2021/1/22 11:19
 */
public class UserService {

    private UserDao dao = new UserDao();

    //注册
    public boolean regist(User user){
        try {
            return dao.insert(user)>0 ? true :false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //激活
    public boolean active(String code){
        try {
            return dao.updateState(code)>0 ? true :false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
