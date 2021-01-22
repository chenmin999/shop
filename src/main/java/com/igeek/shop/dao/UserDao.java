package com.igeek.shop.dao;

import com.igeek.shop.entity.User;

import java.sql.SQLException;

/**
 * @version 1.0
 * @Description 用户模块的数据交互类
 * @Author chenmin
 * @Date 2021/1/22 11:15
 */
public class UserDao extends BasicDao<User> {

    //插入用户数据
    public int insert(User user) throws SQLException {
        String sql = "insert into user values(?,?,?,?,?,?,?,?,0,?,?)";
        int i = this.update(sql, user.getUid(), user.getUsername(), user.getPassword(),
                user.getName(), user.getEmail(), user.getTelephone(),
                user.getBirthday(), user.getSex(), user.getCode(), user.getAddress());
        return i;
    }

}
