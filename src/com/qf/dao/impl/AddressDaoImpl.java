package com.qf.dao.impl;

import com.qf.dao.AddressDao;
import com.qf.entity.Address;
import com.qf.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 小灰灰呀
 */
public class AddressDaoImpl implements AddressDao {

    /**
     * 1. 查询地址信息
     *
     * @param uid
     * @return
     * @throws SQLException
     */
    public List<Address> selectAddressByUid(int uid) throws SQLException {
        //1.创建 QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        //2.编写sql
        String sql = "select a_id as aid , u_id as uid , a_name as aname ," +
                "a_phone as aphone , a_detail as adetail , a_state as astate " +
                "from address where u_id = ? ;";

        //3.执行sql
        return queryRunner.query(sql, new BeanListHandler<>(Address.class), uid);
    }


    /**
     * 2.插入收货地址
     *
     * @param address
     * @throws SQLException
     */
    @Override
    public void insertAddress(Address address) throws SQLException {

        //1.创建 QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        //2.编写sql
        String sql = "insert into address (u_id , a_name , a_phone , a_detail , a_state) value ( ? , ? , ? , ? , ? );";

        //3.执行sql
        queryRunner.update(sql, address.getUid(), address.getAname(), address.getAphone(), address.getAdetail(), address.getAstate());
    }

    /**
     * 3.设置默认收货地址
     *
     * @param aid
     * @param uid
     * @throws SQLException
     */
    @Override
    public void updateAddressToDefault(String aid, int uid) throws SQLException {
        //1.创建 QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        //2.编写sql
        String sql1 = "update address set a_state=1 where a_id = ? ";
        String sql2 = "update address set a_state=0 where a_id  != ? and u_id = ?;";

        //3.执行sql
        queryRunner.update(sql1, aid);
        queryRunner.update(sql2, aid, uid);
    }

    /**
     * 4.删除收货地址
     *
     * @param aid
     * @throws SQLException
     */
    @Override
    public void deleteAddressByAid(String aid) throws SQLException {

        //1.创建 QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        //2.编写sql
        String sql = "delete from address where a_id = ? ;";

        //3.执行sql
        queryRunner.update(sql, aid);
    }


    /**
     * 5.通过aid更改收货地址
     *
     * @param address
     * @throws SQLException
     */
    @Override
    public void updateByAid(Address address) throws SQLException {
        //1.创建 QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        //2.编写sql
        String sql = "update address set a_state = ? , a_name = ? , a_phone = ? , a_detail = ? where a_id = ? ;";

        //3.执行sql
        queryRunner.update(sql, address.getAstate(), address.getAname(), address.getAphone(), address.getAdetail(), address.getAid());
    }
}
