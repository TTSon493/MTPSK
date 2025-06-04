package DAO;

import DTO.Account;
import DTO.News;
import Utils.DBUtils;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author trant
 */
public class NewsDAO {

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

    public ArrayList<News> newsList() throws SQLException, Exception {
        ArrayList<News> newsList = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select News.news_id , News.title, News.image, News.date, News.description, Account.full_name\n"
                        + "from News \n"
                        + "inner join Account on Account.user_id = News.user_id";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    News list = new News(rs.getInt("news_id"), rs.getString("title"), rs.getBytes("image"), rs.getDate("date"), rs.getString("description"), rs.getString("full_name"));
                    newsList.add(list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return newsList;
    }

    public News addNews(String title, InputStream image, java.sql.Date date, int userId, String description) throws SQLException, Exception {
        News user = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "insert into News(title, image, date, description, user_id) \n"
                        + "values (?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, title);
                ps.setBinaryStream(2, image);
                ps.setDate(3, date);
                ps.setString(4, description);
                ps.setInt(5, userId);
                ps.executeUpdate();
                user = new News(title, date, description, userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return user;
    }

}
