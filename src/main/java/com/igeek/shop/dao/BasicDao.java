package com.igeek.shop.dao;

import com.igeek.shop.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

/**
 * @version 1.0
 * @Description TODO
 * @Author chenmin
 * @Date 2021/1/22 11:13
 */
public class BasicDao<T> {

    QueryRunner runner = new QueryRunner();

    //增删改
    public int update(String sql , Object...params) throws SQLException {
        return runner.update(DataSourceUtils.getConnection(),sql,params);
    }

    //查询单行单列的值
    public Object getSingleValue(String sql , Object...params) throws SQLException {
        return runner.query(DataSourceUtils.getConnection(),sql,new ScalarHandler<>(),params);
    }
}
