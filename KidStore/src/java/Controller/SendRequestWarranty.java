package Controller;

import DAO.OrderDAO;
import DTO.OrderWarranty;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author trant
 */
public class SendRequestWarranty extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String customerAddress = request.getParameter("customerAddress");
            String customerPhone = request.getParameter("customerPhone");
            String issueDescription = request.getParameter("issueDescription");
            int orderDetailId = Integer.parseInt(request.getParameter("orderDetailId"));
            int userId = Integer.parseInt(request.getParameter("userId"));
            OrderDAO orderDAO = new OrderDAO();
            OrderWarranty sendRequestWarranty = orderDAO.sendRequestWarranty(orderDetailId, customerAddress, customerPhone, issueDescription);
            if (sendRequestWarranty != null) {
                request.setAttribute("SEND_REQUEST_SUCCESS", "Đã Gửi Đơn Bảo Hành");
            } else {
                request.setAttribute("SEND_REQUEST_FAILED", "Gửi Đơn Bảo Hành Thất Bại");
            }
            
            response.sendRedirect("LoadWarrantyController?userId=" + userId);
            
        } catch (Exception ex) {
            Logger.getLogger(SendRequestWarranty.class.getName()).log(Level.SEVERE, null, ex);  
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
