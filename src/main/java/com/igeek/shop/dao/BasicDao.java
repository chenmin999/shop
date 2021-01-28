package com.igeek.shop.dao;

import com.igeek.shop.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

    //查询列表
    public List<T> getBeanList(String sql , Class<T> clazz , Object...params) throws SQLException {
        return runner.query(DataSourceUtils.getConnection(),sql,new BeanListHandler<>(clazz),params);
    }

    //查询单个对象
    public T getBean(String sql , Class<T> clazz , Object...params) throws SQLException {
        return runner.query(DataSourceUtils.getConnection(),sql,new BeanHandler<>(clazz),params);
    }

    //查询不同类型的列表
    public List<Map<String,Object>> getMapList(String sql,Object...params) throws SQLException {
        return runner.query(DataSourceUtils.getConnection(),sql,new MapListHandler(),params);
    }
}
