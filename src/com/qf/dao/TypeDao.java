package com.qf.dao;

import com.qf.entity.Type;

import java.sql.SQLException;
import java.util.List;

public interface TypeDao {
    //需要一个方法查询数据库类别表，返回Type
    List<Type> selectAll() throws SQLException;
}
