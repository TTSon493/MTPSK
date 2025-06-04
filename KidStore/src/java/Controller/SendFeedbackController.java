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
public class SendFeedbackController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int userId = Integer.parseInt(request.getParameter("userId"));
            String issueDescription = request.getParameter("issueDescription");
            OrderDAO orderDAO = new OrderDAO();
            OrderWarranty sendFeedback = orderDAO.sendFeedback(orderId, issueDescription);
            if (sendFeedback != null) {
                request.setAttribute("SEND_FEEDBACK_SUCCESS", "Đã Gửi Feedback");
            } else {
                request.setAttribute("SEND_FEEDBACK_FAILED", "Gửi Feedback Thất Bại");
            }
            RequestDispatcher rd = request.getRequestDispatcher("LoadOrderHistoryController?userId=" + userId);
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SendFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
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
