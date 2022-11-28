package com.qf.dao;

import com.qf.entity.Type;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 小灰灰呀
 */
public interface TypeDao {
    /**
     * 需要一个方法查询数据库类别表，返回Type
     * @return
     * @throws SQLException
     */
    List<Type> selectAll() throws SQLException;
}
