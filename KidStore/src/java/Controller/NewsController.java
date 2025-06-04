package Controller;

import DAO.NewsDAO;
import DTO.News;
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
public class NewsController extends HttpServlet {
    
    private static final String NEWS = "news.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            String url = NEWS;
            NewsDAO newsDAO = new NewsDAO();
            ArrayList<News> newsList = newsDAO.newsList();
            if (newsList != null){
                session.setAttribute("NEWS_LIST", newsList);
                
            } else {
                session.setAttribute("NEWS_LIST_ERROR", "Error load news");
            }
            
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
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
