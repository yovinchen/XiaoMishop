package com.qf.service;

import com.qf.entity.Address;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 小灰灰呀
 */
public interface AddressService {
    /**
     * @param uid
     * @return
     * @throws SQLException
     */
    List<Address> findAddressByUid(int uid) throws SQLException;

    /**
     * @param address
     * @throws SQLException
     */
    void saveAddress(Address address) throws SQLException;

    /**
     * @param aid
     * @param uid
     * @throws SQLException
     */
    void setAddressToDefault(String aid, int uid) throws SQLException;

    /**
     * @param aid
     * @throws SQLException
     */
    void deleteAddressByAid(String aid) throws SQLException;

    /**
     * @param address
     * @throws SQLException
     */
    void updateByAid(Address address) throws SQLException;
}
