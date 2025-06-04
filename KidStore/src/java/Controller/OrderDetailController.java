/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.Code;
import DAO.OrderDAO;
import DAO.ToyDAO;
import DTO.Toy;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
public class OrderDetailController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
        HttpSession session = request.getSession();
        int toyId, quantity;
        double price;
        String warrantyCode;
        Code code = new Code();
        ToyDAO tdao = new ToyDAO();
        OrderDAO dao = new OrderDAO();
        int orderId = (Integer)session.getAttribute("OrderID");
        HashMap<Toy,Integer> cartList = (HashMap<Toy,Integer>) session.getAttribute("cartList");
        for(HashMap.Entry<Toy, Integer> c : cartList.entrySet()){
            toyId = c.getKey().getToyId();
            quantity = c.getValue();
            price = c.getKey().getPrice()*c.getKey().getDiscount();
            warrantyCode = code.Code();
            int a = dao.CreateOrderDetail(toyId, quantity, price, orderId, warrantyCode, "Đang xử lý");
            int b = tdao.ReduceToyQuantityAfterSell(toyId, quantity);
            System.out.println(a);
            System.out.println(b);
        }
        session.setAttribute("cartList", null);
        session.setAttribute("OrderID", null);
        session.setAttribute("discount", null);
        session.setAttribute("vouchernamecode", null);
        session.setAttribute("DataStore", null);
        session.setAttribute("voucherID", null);
        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
        rd.forward(request, response);
        }
        catch(Exception e){
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
