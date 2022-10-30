package com.qf.service;

import com.qf.entity.Type;

import java.sql.SQLException;
import java.util.List;

public interface TypeService {

    //查询返回所有类别表中对应的type信息
    List<Type> findAll() throws SQLException;

}
