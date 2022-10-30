package com.qf.service.impl;

import com.qf.dao.TypeDao;
import com.qf.dao.impl.TypeDaoImpl;
import com.qf.entity.Type;
import com.qf.service.TypeService;

import java.sql.SQLException;
import java.util.List;

public class TypeServiceImpl implements TypeService {

    @Override
    public List<Type> findAll() throws SQLException {
        //这里调用TypeDao接口（Dao层）
        TypeDao typeDao = new TypeDaoImpl();
        List<Type> types = typeDao.selectAll();
        return types;
    }
}
