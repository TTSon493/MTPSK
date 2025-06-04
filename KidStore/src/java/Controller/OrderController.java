/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AccountDAO;
import DAO.OrderDAO;
import DTO.Account;
import DTO.DataStore;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
public class OrderController extends HttpServlet {

    private final static String DETAIL = "OrderDetailController";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean type = true;
        String url = DETAIL;
        String name, phone, address;
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            int voucher_id = 1;
            Account a = (Account) session.getAttribute("acc");
            if (a != null) {
                String paymentType = request.getParameter("paymentType");
                if(paymentType==null){
                paymentType = (String)session.getAttribute("callPaymentType");
                }
                session.setAttribute("callPaymentType", paymentType);
                if(paymentType==null){
                    request.setAttribute("ERROR_NOT_CHOOSING", "You didn't set your payment details");
                    RequestDispatcher rd = request.getRequestDispatcher("checkout.jsp");
                    rd.forward(request, response);
                }
                name = request.getParameter("name");
                phone = request.getParameter("phone");
                address = request.getParameter("address");
                String paymentMethod = request.getParameter("paymentMethod");
                if(paymentType.equals("selfPayment")){
                    AccountDAO dao = new AccountDAO();
                    if(!dao.checkFullName(name, phone, address)){
                        name =a.getFullName();
                        phone = a.getPhone();
                        address = a.getAddress();
                    }
                }
                if (name != null && phone != null && address != null && paymentMethod != null) {
                    DataStore ds = new DataStore(name, phone, address, paymentMethod);
                    session.setAttribute("DataStore", ds);
                }
                DataStore data = (DataStore) session.getAttribute("DataStore");
                if (name == null || phone == null || address == null || paymentMethod == null) {
                    name = data.getName();
                    phone = data.getPhone();
                    address = data.getAddress();
                    paymentMethod = data.getPaymentType();
                }
                double amount = (Double) session.getAttribute("orderAmount");
                String transaction_status = request.getParameter("vnp_ResponseCode");
                String voucher_idParam = (String) session.getAttribute("voucherID");
                if (voucher_idParam != null) {
                    voucher_id = Integer.parseInt(voucher_idParam);
                }
                if (paymentMethod.equals("cashOnDelivery")) {
                    type = false;
                    OrderDAO dao = new OrderDAO();
                    int OrderId = dao.CreateNewOrder(name, phone, address, type, voucher_id, a.getUserId(), amount);
                    session.setAttribute("OrderID", OrderId);
                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                } else if (type) {
                    if (transaction_status == null) {
                        url = "vnpay_pay.jsp";
                        RequestDispatcher rd = request.getRequestDispatcher(url);
                        rd.forward(request, response);
                    } else if (transaction_status.equals("00")) {
                        OrderDAO dao = new OrderDAO();
                        int OrderId = dao.CreateNewOrder(name, phone, address, type, voucher_id, a.getUserId(), amount);
                        session.setAttribute("OrderID", OrderId);
                        RequestDispatcher rd = request.getRequestDispatcher(url);
                        rd.forward(request, response);
                    } else {
                        session.setAttribute("ERROR_MESSAGE", "Your Transaction wasn't completed");
                    }
                }            
            } else {
                url = "login.jsp";
                request.setAttribute("LOGIN_ERROR", "Login to use our cart");
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
