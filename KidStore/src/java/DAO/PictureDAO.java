/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Image;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class PictureDAO {
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
    public ArrayList<Image> getImageByToyId(int id){
        ArrayList<Image> list = new ArrayList<>();
        try{
            con = DBUtils.getConnection();
            if(con!=null){
                String sql = "select image_id, image_toy, main from Image\n"
                        + "where toy_id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while(rs.next()){
                    Image image = new Image(rs.getInt("image_id"),
                            rs.getBytes("image_toy"), id, rs.getBoolean("main"));
                    list.add(image);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
