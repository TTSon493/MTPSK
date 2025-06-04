package DAO;

/**
 *
 * @author trant
 */
import DTO.Account;
import DTO.Category;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import Utils.DBUtils;
import java.util.ArrayList;

public class CategoryDAO {

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

    public ArrayList<Category> categoryList() throws SQLException, Exception {
        ArrayList<Category> listCategory = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select category_id ,category_name \n"
                        + "from Category \n"
                        + "where isActive = 1";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Category list = new Category(rs.getInt("category_id"), rs.getString("category_name"));
                    listCategory.add(list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return listCategory;
    }

    public ArrayList<Category> categoryListNotApprove() throws SQLException, Exception {
        ArrayList<Category> listCategory = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select Category.category_id , Category.category_name, Account.full_name\n"
                        + "from Category \n"
                        + "inner join Account on Account.user_id = Category.user_id\n"
                        + "where Category.isActive = 0 and Category.isDisable = 0";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Category list = new Category(rs.getInt("category_id"), rs.getString("category_name"), rs.getString("full_name"));
                    listCategory.add(list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return listCategory;
    }

    public ArrayList<Category> categoryListAll() throws SQLException, Exception {
        ArrayList<Category> listCategory = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select category_id ,category_name, isActive from Category where isDisable = 0";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Category list = new Category(rs.getInt("category_id"), rs.getString("category_name"), rs.getInt("isActive"),0);
                    listCategory.add(list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return listCategory;
    }

    public Category addCategory(String nameCategory, int userId) throws SQLException, Exception {
        Category category = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "insert into Category(category_name, user_id, isActive, isDisable) values (?,?,0,0)";
                ps = con.prepareStatement(sql);
                ps.setString(1, nameCategory);
                ps.setInt(2, userId);
                ps.executeUpdate();
                category = new Category(nameCategory, userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return category;
    }

    public Category editCategory(int idCategory, String nameCategory) throws SQLException, Exception {
        Category category = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "update Category\n"
                        + "set category_name = ?\n"
                        + "where category_id = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, nameCategory);
                ps.setInt(2, idCategory);
                ps.executeUpdate();
                category = new Category(idCategory, nameCategory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return category;
    }

    public Category approveCategory(int categoryId) throws SQLException, Exception {
        Category category = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "update Category\n"
                        + "set isActive = 1\n"
                        + "where category_id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, categoryId);
                ps.executeUpdate();
                category = new Category(categoryId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return category;
    }

    public Category cancelCategory(int categoryId) throws SQLException, Exception {
        Category category = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "update Category\n"
                        + "set isDisable = 1\n"
                        + "where category_id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, categoryId);
                ps.executeUpdate();
                category = new Category(categoryId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return category;
    }

}
