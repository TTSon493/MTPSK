package Controller;

import DAO.NewsDAO;
import DTO.News;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
public class AddNewsController extends HttpServlet {

    private static final String NEWS = "AddNew.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            response.setContentType("text/html;charset=UTF-8");
            String url = NEWS;
            String title = request.getParameter("title");
            Part filePart = request.getPart("image");
            String dateStr = request.getParameter("date");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = dateFormat.parse(dateStr);
            java.sql.Date date = new java.sql.Date(utilDate.getTime());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, 2);
            java.util.Date newDate = calendar.getTime();
            java.sql.Date newSqlDate = new java.sql.Date(newDate.getTime());
            int userId = Integer.parseInt(request.getParameter("userId"));
            String description = request.getParameter("description");
            NewsDAO newsDAO = new NewsDAO();
            News addNews = newsDAO.addNews(title, filePart.getInputStream(), newSqlDate, userId, description);

            if (addNews != null) {
                request.setAttribute("ADD_NEWS_SUCCESS", "Add News Success");

            } else {
                request.setAttribute("ADD_NEWS_FAILED", "Add News Failed");
            }

            ArrayList<News> newsList = newsDAO.newsList();
            if (newsList != null) {
                session.setAttribute("NEWS_LIST", newsList);

            } else {
                request.setAttribute("NEWS_LIST_ERROR", "Error load news");
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
