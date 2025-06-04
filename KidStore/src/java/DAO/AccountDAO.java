package DAO;

/**
 *
 * @author trant
 */
import DTO.Account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import Utils.DBUtils;
import java.util.ArrayList;

public class AccountDAO {

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

    public Account signup(String userName, String password, String fullName, String phone, String address) throws SQLException, Exception {
        Account user = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "insert into Account (username, password, full_name, phone, address, isActive, role_id) values (?,?,?,?,?,1,3)";
                ps = con.prepareStatement(sql);
                ps.setString(1, userName);
                ps.setString(2, password);
                ps.setString(3, fullName);
                ps.setString(4, phone);
                ps.setString(5, address);
                ps.executeUpdate();
                user = new Account(userName, password, fullName, phone, address);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return user;
    }

    public ArrayList<Account> getAllAccount() throws Exception {
        ArrayList<Account> allAccount = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Select Account.user_id, Account.username, Account.full_name, "
                        + "Account.phone, Account.address, Account.isActive,Account.role_id, "
                        + "Role.role_name from Account\n"
                        + "left join Role on Account.role_id = Role.role_id";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Account acc = new Account(rs.getInt("user_id"), rs.getString("username"),
                            rs.getString("full_name"), rs.getString("phone"),
                            rs.getString("address"), rs.getBoolean("isActive"),
                            rs.getInt("role_id"), rs.getString("role_name"));
                    allAccount.add(acc);
                }
                System.out.println(allAccount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return allAccount;
    }

    public void UpdateActiveAccount(int user_id, boolean active) throws Exception {
        try {
            con = DBUtils.getConnection();
            String sql = "Update Account Set isActive=? where user_id=?";
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, active);
            ps.setInt(2, user_id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection();
        }
    }

    public Account addNewAccount(String userName, String password, String fullName, String phone, String address, int roleId) throws SQLException, Exception {
        Account user = null;
        try {
            System.out.println("addNewAccount");
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "insert into Account (username, password, full_name, phone, address, isActive, role_id) values (?,?,?,?,?,1,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, userName);
                ps.setString(2, password);
                ps.setString(3, fullName);
                ps.setString(4, phone);
                ps.setString(5, address);
                ps.setInt(6, roleId);
                ps.executeUpdate();
                user = new Account(userName, password, fullName, phone, address, roleId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return user;
    }

    public void UpdateRoleAccount(int user_id, int role) throws Exception {
        try {
            con = DBUtils.getConnection();
            String sql = "Update Account Set role_id=? where user_id=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, role);
            ps.setInt(2, user_id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection();
        }
    }

    public boolean checkUsername(String name) throws Exception {
        boolean check = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select user_id from Account where username =?";
                ps = con.prepareStatement(sql);
                ps.setString(1, name);
                rs = ps.executeQuery();
                while (rs.next()) {
                    check = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean checkFullName(String name, String phone, String address) throws Exception {
        boolean check = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select user_id from Account where full_name =? and phone=? and address=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, phone);
                ps.setString(3, address);
                rs = ps.executeQuery();
                while (rs.next()) {
                    check = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return check;
    }
}
