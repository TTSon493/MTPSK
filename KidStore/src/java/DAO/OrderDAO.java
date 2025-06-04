/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Order;
import DTO.OrderDashboard;
import DTO.OrderDetail;
import DTO.OrderHistory;
import DTO.OrderSold;
import DTO.OrderWarranty;
import DTO.Toy;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author admin
 */
public class OrderDAO {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private Date addTwoDays(Date date) {
        Date dateAfter = null;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, 2);
            dateAfter = cal.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateAfter;
    }

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

    public int CreateNewOrder(String name, String phone, String address,
            boolean paymentType, int voucher_id, int user_id, double order_amount) throws Exception {
        int THEOrderId = -1;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO [dbo].[Order]\n"
                        + "           ([user_id]\n"
                        + "           ,[voucher_id]\n"
                        + "           ,[payment_type]\n"
                        + "           ,[order_amount]\n"
                        + "           ,[name]\n"
                        + "           ,[phone]\n"
                        + "           ,[address]\n"
                        + "           ,[status_order])\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,N'Đang Xử Lí')";
                ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, user_id);
                ps.setInt(2, voucher_id);
                ps.setBoolean(3, paymentType);
                ps.setDouble(4, order_amount);
                ps.setString(5, name);
                ps.setString(6, phone);
                ps.setString(7, address);
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    THEOrderId = rs.getInt(1);
                    System.out.println("Newly created order ID (getGeneratedKeys): " + THEOrderId);
                } else {
                    throw new SQLException("Failed to retrieve generated order ID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return THEOrderId;

    }

    public int CreateOrderDetail(int toyId, int quantity, double price, int orderId, String warrantyCode, String status) throws Exception {
        int work = 0;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO OrderDetail "
                        + "(toy_id, quantity, OD_price, order_id, warranty_code, status) "
                        + "VALUES (?, ?, ?, ?, ?, N'Không Có Yêu Cầu Bảo Hành')";
                ps = con.prepareStatement(sql);
                ps.setInt(1, toyId);
                ps.setInt(2, quantity);
                ps.setDouble(3, price);
                ps.setInt(4, orderId);
                ps.setString(5, warrantyCode);
                work = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return work;
    }

    public ArrayList<OrderDetail> getAllOrderDetail() throws Exception {
        ArrayList<OrderDetail> detailList = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select Toy.toy_name,OrderDetail.quantity,"
                        + "OrderDetail.OD_price, OrderDetail.order_detail_id,"
                        + "OrderDetail.order_id, OrderDetail.warranty_code, Order.order_date"
                        + "from Toy\n"
                        + "inner join OrderDetail on Toy.toy_id = OrderDetail.toy_id\n"
                        + "inner join Order on OrderDetail.order_id = Order.order_id\n"
                        + "where Order.status = 'Complete'";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    OrderDetail list = new OrderDetail(rs.getInt("order_detail_id"),
                            rs.getString("toy_name"), rs.getInt("quantity"),
                            rs.getDouble("price"), rs.getInt("order_id"),
                            rs.getDate("orderDate"), rs.getString("customerName"));
                    detailList.add(list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return detailList;
    }

    public ArrayList<OrderHistory> idOrderList() throws SQLException, Exception {
        ArrayList<OrderHistory> idOrderList = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select [Order].order_id, [Order].order_date, [Order].status_order, [Order].order_amount, [Order].name, [Order].phone, [Order].address, [Order].payment_type\n"
                        + "from [Order]";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    OrderHistory list = new OrderHistory(rs.getInt("order_id"), addTwoDays(rs.getDate("order_date")), rs.getString("status_order"), rs.getDouble("order_amount"), rs.getString("name"), rs.getString("phone"), rs.getString("address"), rs.getInt("payment_type"));
                    idOrderList.add(list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return idOrderList;
    }

    public ArrayList<OrderHistory> orderHistory() throws SQLException, Exception {
        ArrayList<OrderHistory> listOrder = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select [Order].order_id, "
                        + "[Order].order_date, Image.image_toy, "
                        + "Toy.toy_name, OrderDetail.quantity, Category.category_name,"
                        + " Toy.description, [Order].status_order, "
                        + "Toy.price, OrderDetail.OD_price, [Order].order_amount, Toy.discount\n"
                        + "from Toy \n"
                        + "inner join OrderDetail on Toy.toy_id = OrderDetail.toy_id\n"
                        + "inner join [Order] on OrderDetail.order_id = [Order].order_id\n"
                        + "inner join Image on Toy.toy_id = Image.toy_id\n"
                        + "inner join Category on Toy.category_id = Category.category_id\n"
                        + "where Toy.category_id = Category.category_id";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    OrderHistory list = new OrderHistory(rs.getInt("order_id"), 
                            addTwoDays(rs.getDate("order_date")), 
                            rs.getBytes("image_toy"), rs.getString("toy_name"), 
                            rs.getInt("quantity"), rs.getString("category_name"), 
                            rs.getString("description"), rs.getString("status_order"), rs.getDouble("price"), rs.getDouble("OD_price"), rs.getDouble("order_amount"), rs.getDouble("discount"));
                    listOrder.add(list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return listOrder;
    }

    public ArrayList<OrderHistory> orderHistoryByUserId(int userId) throws SQLException, Exception {
        ArrayList<OrderHistory> listOrder = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select [Order].order_id, [Order].order_date, OrderDetail.order_detail_id,  "
                        + "Image.image_toy, Toy.toy_name, OrderDetail.quantity,"
                        + " Category.category_name, Toy.description, "
                        + "[Order].status_order, Toy.price, OrderDetail.OD_price, "
                        + "[Order].order_amount\n"
                        + "from Toy \n"
                        + "inner join OrderDetail on Toy.toy_id = OrderDetail.toy_id\n"
                        + "inner join [Order] on OrderDetail.order_id = [Order].order_id\n"
                        + "inner join Image on Toy.toy_id = Image.toy_id\n"
                        + "inner join Category on Toy.category_id = Category.category_id\n"
                        + "inner join Account on Account.user_id = [Order].user_id\n"
                        + "where Toy.category_id = Category.category_id and Account.user_id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    OrderHistory list = new OrderHistory(rs.getInt("order_id"), rs.getInt("order_detail_id"),
                            rs.getDate("order_date"), rs.getBytes("image_toy"),
                            rs.getString("toy_name"), rs.getInt("quantity"),
                            rs.getString("category_name"), rs.getString("description"),
                            rs.getString("status_order"), rs.getDouble("price"),
                            rs.getDouble("OD_price"), rs.getDouble("order_amount"));
                    listOrder.add(list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return listOrder;
    }

    public ArrayList<OrderWarranty> orderFeedback() throws SQLException, Exception {
        ArrayList<OrderWarranty> listFeedback = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select OrderDetail.order_detail_id , Toy.toy_name, Category.category_name, OrderDetail.OD_price, OrderDetail.feedback\n"
                        + "from Toy \n"
                        + "inner join OrderDetail on Toy.toy_id = OrderDetail.toy_id\n"
                        + "inner join [Order] on OrderDetail.order_id = [Order].order_id\n"
                        + "inner join Category on Toy.category_id = Category.category_id\n"
                        + "where Toy.category_id = Category.category_id and [Order].status_order like N'Đã Giao Hàng'";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    OrderWarranty list = new OrderWarranty(rs.getInt("order_detail_id"), rs.getString("toy_name"), rs.getString("category_name"), rs.getDouble("OD_price"), rs.getString("feedback"));
                    listFeedback.add(list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return listFeedback;
    }

    public OrderWarranty sendFeedback(int orderDetailId, String feedback) throws SQLException, Exception {
        OrderWarranty sendFeedback = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "update OrderDetail\n"
                        + "set feedback = ?\n"
                        + "where order_id = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, feedback);
                ps.setInt(2, orderDetailId);
                ps.executeUpdate();
                sendFeedback = new OrderWarranty(orderDetailId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return sendFeedback;
    }

    public ArrayList<OrderWarranty> orderWarranty() throws SQLException, Exception {
        ArrayList<OrderWarranty> listWarranty = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select OrderDetail.[order_detail_id], [Order].order_date, Image.image_toy, Toy.toy_name, OrderDetail.quantity, OrderDetail.status, Toy.warranty_time, [Order].phone, [Order].address, OrderDetail.warranty_code, [Order].name, OrderDetail.description_warranty\n"
                        + "from Toy \n"
                        + "inner join OrderDetail on Toy.toy_id = OrderDetail.toy_id\n"
                        + "inner join [Order] on OrderDetail.order_id = [Order].order_id\n"
                        + "inner join Image on Toy.toy_id = Image.toy_id\n"
                        + "inner join Category on Toy.category_id = Category.category_id\n"
                        + "inner join Account on Account.user_id = [Order].user_id\n"
                        + "where Toy.category_id = Category.category_id and [Order].status_order like N'Đã Giao Hàng'";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    OrderWarranty list = new OrderWarranty(rs.getInt("order_detail_id"), rs.getDate("order_date"), rs.getBytes("image_toy"), rs.getString("toy_name"), rs.getInt("quantity"), rs.getString("status"), rs.getDate("warranty_time"), rs.getString("phone"), rs.getString("address"), rs.getString("warranty_code"), rs.getString("name"), rs.getString("description_warranty"));
                    listWarranty.add(list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return listWarranty;
    }

    public ArrayList<OrderWarranty> orderWarrantyByUserId(int userId) throws SQLException, Exception {
        ArrayList<OrderWarranty> listWarranty = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select OrderDetail.[order_detail_id], [Order].order_date, Image.image_toy, Toy.toy_name, OrderDetail.quantity, OrderDetail.status, Toy.warranty_time, [Order].phone, [Order].address, OrderDetail.warranty_code, [Order].name, OrderDetail.description_warranty\n"
                        + "from Toy \n"
                        + "inner join OrderDetail on Toy.toy_id = OrderDetail.toy_id\n"
                        + "inner join [Order] on OrderDetail.order_id = [Order].order_id\n"
                        + "inner join Image on Toy.toy_id = Image.toy_id\n"
                        + "inner join Category on Toy.category_id = Category.category_id\n"
                        + "inner join Account on Account.user_id = [Order].user_id\n"
                        + "where Toy.category_id = Category.category_id and Account.user_id = ? and [Order].status_order like N'Đã Giao Hàng'";
                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    OrderWarranty list = new OrderWarranty(rs.getInt("order_detail_id"), rs.getDate("order_date"), rs.getBytes("image_toy"), rs.getString("toy_name"), rs.getInt("quantity"), rs.getString("status"), rs.getDate("warranty_time"), rs.getString("phone"), rs.getString("address"), rs.getString("warranty_code"), rs.getString("name"), rs.getString("description_warranty"));
                    listWarranty.add(list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return listWarranty;
    }

    public OrderHistory processingOrder(int orderId) throws SQLException, Exception {
        OrderHistory order = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "update [Order]\n"
                        + "set status_order = N'Đang Xử Lí'\n"
                        + "where order_id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, orderId);
                ps.executeUpdate();
                order = new OrderHistory(orderId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return order;
    }

    public OrderHistory shippingOrder(int orderId) throws SQLException, Exception {
        OrderHistory order = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "update [Order]\n"
                        + "set status_order = N'Đang Giao Hàng'\n"
                        + "where order_id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, orderId);
                ps.executeUpdate();
                order = new OrderHistory(orderId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return order;
    }

    public OrderHistory deliveredOrder(int orderId) throws SQLException, Exception {
        OrderHistory order = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "update [Order]\n"
                        + "set status_order = N'Đã Giao Hàng'\n"
                        + "where order_id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, orderId);
                ps.executeUpdate();
                order = new OrderHistory(orderId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return order;
    }

    public OrderHistory refuseOrder(int orderId) throws SQLException, Exception {
        OrderHistory order = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "update [Order]\n"
                        + "set status_order = N'Từ Chối Bán Hàng'\n"
                        + "where order_id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, orderId);
                ps.executeUpdate();
                order = new OrderHistory(orderId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return order;
    }

    public OrderWarranty processingWarranty(int orderDetailId) throws SQLException, Exception {
        OrderWarranty warranty = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "update OrderDetail\n"
                        + "set status = N'Đang Xử Lí'\n"
                        + "where order_detail_id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, orderDetailId);
                ps.executeUpdate();
                warranty = new OrderWarranty(orderDetailId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return warranty;
    }

    public OrderWarranty receivedWarranty(int orderDetailId) throws SQLException, Exception {
        OrderWarranty warranty = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "update OrderDetail\n"
                        + "set status = N'Đã Nhận Hàng'\n"
                        + "where order_detail_id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, orderDetailId);
                ps.executeUpdate();
                warranty = new OrderWarranty(orderDetailId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return warranty;
    }

    public OrderWarranty underWarranty(int orderDetailId) throws SQLException, Exception {
        OrderWarranty warranty = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "update OrderDetail\n"
                        + "set status = N'Đang Bảo Hành'\n"
                        + "where order_detail_id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, orderDetailId);
                ps.executeUpdate();
                warranty = new OrderWarranty(orderDetailId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return warranty;
    }

    public OrderWarranty shippingWarranty(int orderDetailId) throws SQLException, Exception {
        OrderWarranty warranty = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "update OrderDetail\n"
                        + "set status = N'Đang Giao Hàng'\n"
                        + "where order_detail_id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, orderDetailId);
                ps.executeUpdate();
                warranty = new OrderWarranty(orderDetailId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return warranty;
    }

    public OrderWarranty deliveredWarranty(int orderDetailId) throws SQLException, Exception {
        OrderWarranty warranty = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "update OrderDetail\n"
                        + "set status = N'Đã Giao Hàng'\n"
                        + "where order_detail_id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, orderDetailId);
                ps.executeUpdate();
                warranty = new OrderWarranty(orderDetailId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return warranty;
    }

    public OrderWarranty refuseWarranty(int orderDetailId) throws SQLException, Exception {
        OrderWarranty warranty = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "update OrderDetail\n"
                        + "set status = N'Từ Chối Bảo Hành'\n"
                        + "where order_detail_id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, orderDetailId);
                ps.executeUpdate();
                warranty = new OrderWarranty(orderDetailId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return warranty;
    }

    public OrderWarranty sendRequestWarranty(int orderDetailId, String customerAddress, String customerPhone, String issueDescription) throws SQLException, Exception {
        OrderWarranty warranty = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE [Order] \n"
                        + "SET [Order].phone = ?,  [Order].address = ?\n"
                        + "FROM [Order] \n"
                        + "INNER JOIN OrderDetail \n"
                        + "ON [Order].order_id = OrderDetail.order_id \n"
                        + "WHERE OrderDetail.order_detail_id = ?\n"
                        + "\n"
                        + "UPDATE OrderDetail\n"
                        + "SET status = N'Gửi Bảo Hành', description_warranty = ?\n"
                        + "WHERE order_detail_id = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, customerPhone);
                ps.setString(2, customerAddress);
                ps.setInt(3, orderDetailId);
                ps.setString(4, issueDescription);
                ps.setInt(5, orderDetailId);
                ps.executeUpdate();
                warranty = new OrderWarranty(orderDetailId, customerPhone, customerAddress, issueDescription);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return warranty;
    }

    public ArrayList<OrderSold> getAllOrderSold() throws Exception {
        ArrayList<OrderSold> ordersold_list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select [Order].order_id, [Order].name, [Order].phone,\n"
                        + " [Order].address, [Order].payment_type, \n"
                        + "Image.image_toy, Toy.toy_name, OrderDetail.quantity, "
                        + "Account.full_name, [Order].order_date,\n"
                        + "OrderDetail.OD_price, Voucher.voucher_discount from Toy \n"
                        + "inner join OrderDetail on Toy.toy_id = OrderDetail.toy_id\n"
                        + "inner join [Order] on OrderDetail.order_id = [Order].order_id\n"
                        + "inner join Image on Toy.toy_id = Image.toy_id\n"
                        + "inner join Account on [Order].user_id = Account.user_id\n"
                        + "inner join Voucher on [Order].voucher_id = Voucher.id\n"
                        + "where [Order].status_order= N'Đã Giao Hàng'";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    OrderSold os = new OrderSold(rs.getInt("order_id"), rs.getString("name"),
                            rs.getString("phone"), rs.getString("address"),
                            rs.getBoolean("payment_type"), rs.getBytes("image_toy"),
                            rs.getString("toy_name"), rs.getInt("quantity"),
                            rs.getString("full_name"), rs.getDouble("OD_price"),
                            addTwoDays(rs.getDate("order_date")),
                            rs.getDouble("OD_price") * rs.getInt("quantity"),
                            rs.getDouble("voucher_discount"));
                    ordersold_list.add(os);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return ordersold_list;
    }

    public ArrayList<OrderHistory> getAllOrderIDByUserID(int id) throws Exception {
        ArrayList<OrderHistory> listOfOrderID = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select [Order].order_id, [Order].status_order, "
                        + "[Order].order_date, [Order].order_amount, "
                        + "[Order].payment_type, Voucher.voucher_discount from [Order] "
                        + "inner join Voucher on [Order].voucher_id = Voucher.id\n"
                        + "where [Order].user_id=? ORDER BY [Order].order_id DESC;";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    OrderHistory oh = new OrderHistory(rs.getInt("order_id"),
                            addTwoDays(rs.getDate("order_date")), rs.getString("status_order"),
                            rs.getDouble("order_amount"),
                            rs.getDouble("voucher_discount"), rs.getBoolean("payment_type"));
                    listOfOrderID.add(oh);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return listOfOrderID;
    }
    public ArrayList<OrderDashboard> getAllOrderIDByDate(Date date) throws Exception {
        ArrayList<OrderDashboard> listOfOrderID = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select order_id, order_amount, name, phone, address,"
                        + " payment_type from [Order]"
                        + "where order_date=? AND status_order=N'Đã Giao Hàng'";
                ps = con.prepareStatement(sql);
                ps.setDate(1, (java.sql.Date) date);
                rs = ps.executeQuery();
                while (rs.next()) {
                    OrderDashboard oh = new OrderDashboard(rs.getInt("order_id"),
                            date, rs.getDouble("order_amount"), rs.getString("name"),
                            rs.getString("phone"), rs.getString("address"), 
                            rs.getBoolean("payment_type"));
                    listOfOrderID.add(oh);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return listOfOrderID;
    }

    public ArrayList<OrderDashboard> getAllOrderID() throws Exception {
        ArrayList<OrderDashboard> listOfOrderID = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select order_id, order_date,"
                        + "order_amount, name, phone, address, payment_type from [Order]"
                        + " where status_order =N'Đã Giao Hàng'";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    OrderDashboard oh = new OrderDashboard(rs.getInt("order_id"),
                            addTwoDays(rs.getDate("order_date")), rs.getDouble("order_amount"),
                            rs.getString("name"), rs.getString("phone"),
                            rs.getString("address"),rs.getBoolean("payment_type"));
                    listOfOrderID.add(oh);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return listOfOrderID;
    }
   
}
