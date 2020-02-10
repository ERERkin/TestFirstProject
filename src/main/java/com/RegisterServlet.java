package com;

import com.DB.DB;
import com.model.UserStatistic;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserStatistic u=new UserStatistic();
        u.setLogin(req.getParameter("login"));
        u.setEmail(req.getParameter("email"));
        u.setStoryName(req.getParameter("storyName"));
        u.setPassword(req.getParameter("password"));
        boolean result=DB.addUserStatistic(u);
        String url;
        if (result)
            url = "/userPage.jsp";
        else url = "/Fail.jsp";
        RequestDispatcher dispatcher1 = getServletContext().getRequestDispatcher(url);
        dispatcher1.forward(req, resp);

    }
}
