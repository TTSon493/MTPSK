/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Toy;
import DTO.Voucher;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import Utils.DBUtils;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class VoucherDAO {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public Voucher getVoucherByNameCode(String namecode) throws Exception {
        Voucher voucher = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select voucher_id, description, date_expiration, "
                        + "voucher_discount, "
                        + "voucher_limit,voucher_limit_price where voucher_name = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, namecode);
                rs = ps.executeQuery();
                while (rs.next()) {
                    voucher = new Voucher(rs.getInt("voucher_id"), namecode,
                            rs.getString("description"), rs.getDate("date_expiration"),
                            rs.getDouble("voucher_discount"),
                            rs.getInt("voucher_limit"), rs.getDouble("voucher_limit_price"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return voucher;
    }

    public Voucher getVoucherValue(String namecode) throws Exception {
        double discount = 1;
        int id = 1;
        Voucher v = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select id, voucher_discount from Voucher where voucher_name = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, namecode);
                rs = ps.executeQuery();
                while (rs.next()) {
                    discount = rs.getDouble("voucher_discount");
                    id = rs.getInt("id");                
                }
                 v = new Voucher(id, discount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return v;
    }
}
