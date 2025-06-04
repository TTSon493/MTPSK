package Controller;

import DAO.ToyDAO;
import DTO.Toy;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author trant
 */
public class AddToyController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            ToyDAO toyDAO = new ToyDAO();
            String name = request.getParameter("productName");
            Part filePart = request.getPart("image");
            double price = Double.parseDouble(request.getParameter("price"));
            String description = request.getParameter("description");
            int idCategory = Integer.parseInt(request.getParameter("idCategory"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double discount = Double.parseDouble(request.getParameter("discount"));
            String warrantyTimeStr = request.getParameter("warrantyTime");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = dateFormat.parse(warrantyTimeStr);
            java.sql.Date warrantyTime = new java.sql.Date(utilDate.getTime());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(warrantyTime);
            calendar.add(Calendar.DAY_OF_MONTH, 2);
            java.util.Date newWarrantyTime = calendar.getTime();
            java.sql.Date newSqlWarrantyTime = new java.sql.Date(newWarrantyTime.getTime());
            int userId = Integer.parseInt(request.getParameter("userId"));
            Toy addToy = toyDAO.addToy(name, filePart.getInputStream(), price, description, idCategory, quantity, discount, newSqlWarrantyTime, userId);
            if (addToy != null) {
                request.setAttribute("ADD_TOY_SUCCESS", "Added " + addToy.getToyName() + " Success");
            } else {
                request.setAttribute("ADD_TOY_FAILED", "Added " + addToy.getToyName() + " Failed");
            }

            ArrayList<Toy> toyList = toyDAO.toyList();
            session.setAttribute("TOY_LIST", toyList);

            RequestDispatcher rd = request.getRequestDispatcher("ManagerProduct.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddToyController.class.getName()).log(Level.SEVERE, null, ex);
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
