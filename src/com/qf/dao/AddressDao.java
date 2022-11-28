package com.qf.dao;

import com.qf.entity.Address;

import java.sql.SQLException;
import java.util.List;

public interface AddressDao {
    /**
     * @param uid
     * @return
     * @throws SQLException
     */
    List<Address> selectAddressByUid(int uid) throws SQLException;

    /**
     * @param address
     * @throws SQLException
     */
    void insertAddress(Address address) throws SQLException;

    /**
     * @param aid
     * @param uid
     * @throws SQLException
     */
    void updateAddressToDefault(String aid, int uid) throws SQLException;

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
