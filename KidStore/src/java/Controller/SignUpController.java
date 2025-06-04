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
public class SignUpController extends HttpServlet {

    private static final String SUCCESS = "login.jsp";
    private static final String ERROR = "signup.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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
        String url = ERROR;
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String fullName = request.getParameter("fullName");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String confirmPassword = request.getParameter("confirmPassword");

            AccountDAO accountDAO = new AccountDAO();
            if (!accountDAO.checkUsername(userName)) {
                if (password.equals(confirmPassword)) {
                    Account signUp = accountDAO.signup(userName, password, fullName, phone, address);
                    if (signUp != null) {
                        url = SUCCESS;
                        request.setAttribute("SIGNUP_SUCCESS", "Sign Up Success");
                        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                        dispatcher.forward(request, response);
                    } else {
                        request.setAttribute("SIGNUP_ERROR", "Sign Up Failed");
                        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                        dispatcher.forward(request, response);
                    }
                } else {
                    request.setAttribute("PASS_NOT_MATH", "Password And Confirm Password Not Math");
                    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                    dispatcher.forward(request, response);
                }
            } else {
                request.setAttribute("USERNAME_HAVE_BEEN_USED", "Your username had already been taken");
                RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }

        } catch (Exception ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
