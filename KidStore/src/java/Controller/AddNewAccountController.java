package Controller;

import DAO.AccountDAO;
import DTO.Account;
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
public class AddNewAccountController extends HttpServlet {

    private static final String SUCCESS = "AccountController";
    private static final String ERROR = "AccountController";
//    private static final String SUCCESS = "home.jsp";
//    private static final String ERROR = "home.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("home.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String url = ERROR;
        try {
            String userName = request.getParameter("user_name");
            String password = request.getParameter("password");
            String fullName = request.getParameter("full_name");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            int roleId = Integer.parseInt(request.getParameter("roleId"));
            String confirmPassword = request.getParameter("confirmPassword");
            System.out.println("AddNewAccountController");
            AccountDAO accountDAO = new AccountDAO();
            if (!accountDAO.checkUsername(userName)) {
                if (password.equals(confirmPassword)) {
                    Account addNewAccount = accountDAO.addNewAccount(userName, password, fullName, phone, address, roleId);
                    if (addNewAccount != null) {

                        url = SUCCESS;
                        request.setAttribute("ADD_SUCCESS", "Add success");
                    } else {
                        request.setAttribute("ADD_FAIL", "Add Failed");
                    }
                } else {
                    request.setAttribute("PASS_NOT_MATH", "Password And Confirm Password Not Math");
                }
            } else {
                request.setAttribute("USERNAME_HAVE_BEEN_USED", "Your username had already been taken");
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddNewAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
