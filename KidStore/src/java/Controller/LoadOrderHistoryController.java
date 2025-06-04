package Controller;

import DAO.OrderDAO;
import DTO.OrderHistory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author trant
 */
public class LoadOrderHistoryController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            int userId = Integer.parseInt(request.getParameter("userId"));
            OrderDAO orderDAO = new OrderDAO();
            ArrayList<OrderHistory> orderList = orderDAO.orderHistoryByUserId(userId);
                            ArrayList<OrderHistory> ListOfOrderID = orderDAO.getAllOrderIDByUserID(userId);
            if(orderList != null){
                session.setAttribute("ORDER_HISTORY", orderList);
                session.setAttribute("ORDERID_HISTORY", ListOfOrderID);
            } else {
                request.setAttribute("ORDER_HISTORY_ERROR", "Not Found List Order");
            }
            RequestDispatcher rd = request.getRequestDispatcher("profileHistory.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(LoadOrderHistoryController.class.getName()).log(Level.SEVERE, null, ex);
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
