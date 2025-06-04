package Controller;

import DAO.CategoryDAO;
import DTO.Category;
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
public class EditCategoryController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            CategoryDAO categeoryDAO = new CategoryDAO();
            ArrayList<Category> categoryList = categeoryDAO.categoryList();
            session.setAttribute("CATEGORY_LIST", categoryList);
            
            int idCategory = Integer.parseInt(request.getParameter("idCategory"));
            String nameCategory = request.getParameter("nameCategory");
            CategoryDAO cagoryDAO = new CategoryDAO();
            Category editCategory = cagoryDAO.editCategory(idCategory, nameCategory);
            if (editCategory != null) {
                request.setAttribute("EDIT_CATEGORY_SUCCESS", "Edit Success");
            } else {
                request.setAttribute("EDIT_CATEGORY_FAILED", "Edit Failed");
            }
            RequestDispatcher rd = request.getRequestDispatcher("ManagerCategory.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(EditCategoryController.class.getName()).log(Level.SEVERE, null, ex);
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
