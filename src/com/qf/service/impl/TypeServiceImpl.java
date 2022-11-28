package com.qf.service.impl;

import com.qf.dao.TypeDao;
import com.qf.dao.impl.TypeDaoImpl;
import com.qf.entity.Type;
import com.qf.service.TypeService;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 小灰灰呀
 */
public class TypeServiceImpl implements TypeService {

    /**
     * @return
     * @throws SQLException
     */
    @Override
    public List<Type> findAll() throws SQLException {
        //这里调用TypeDao接口（Dao层）
        TypeDao typeDao = new TypeDaoImpl();
        return typeDao.selectAll();
    }
}
