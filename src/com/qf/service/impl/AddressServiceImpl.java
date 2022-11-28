package com.qf.service.impl;

import com.qf.dao.AddressDao;
import com.qf.dao.impl.AddressDaoImpl;
import com.qf.entity.Address;
import com.qf.service.AddressService;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 小灰灰呀
 */
public class AddressServiceImpl implements AddressService {
    /**
     * 1.新增收货地址
     *
     * @param uid
     * @return
     * @throws SQLException
     */
    @Override
    public List<Address> findAddressByUid(int uid) throws SQLException {
        AddressDao addressDao = new AddressDaoImpl();
        return addressDao.selectAddressByUid(uid);
    }

    /**
     * 2.插入收货地址
     *
     * @param address
     * @throws SQLException
     */
    @Override
    public void saveAddress(Address address) throws SQLException {
        AddressDao addressDao = new AddressDaoImpl();
        addressDao.insertAddress(address);
    }

    /**
     * 3.设置默认收货地址
     *
     * @param aid
     * @param uid
     * @throws SQLException
     */
    @Override
    public void setAddressToDefault(String aid, int uid) throws SQLException {
        AddressDao addressDao = new AddressDaoImpl();
        addressDao.updateAddressToDefault(aid, uid);
    }

    /**
     * 4.删除收货地址
     *
     * @param aid
     * @throws SQLException
     */
    @Override
    public void deleteAddressByAid(String aid) throws SQLException {
        AddressDao addressDao = new AddressDaoImpl();
        addressDao.deleteAddressByAid(aid);
    }

    /**
     * 5.修改收货地址
     *
     * @param address
     * @throws SQLException
     */
    @Override
    public void updateByAid(Address address) throws SQLException {
        AddressDao addressDao = new AddressDaoImpl();
        addressDao.updateByAid(address);
    }
}
