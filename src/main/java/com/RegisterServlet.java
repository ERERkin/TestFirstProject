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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserStatistic u=new UserStatistic(0, req.getParameter("login"),
                req.getParameter("email"), req.getParameter("storyName"),
                req.getParameter("password"));
        u.setLogin(req.getParameter("login"));
        u.setEmail(req.getParameter("ema"));
        u.setStoryName(req.getParameter("storyName"));
        u.setPassword(req.getParameter("password"));
        System.out.println(u.getLogin());
        System.out.println(u.getEmail());
        System.out.println(u.getStoryName());
        System.out.println(u.getPassword());
        boolean result=DB.addUserStatistic(u);
        String url;
        if (result)
            url = "/userPage.jsp";
        else url = "/Fail.jsp";
        RequestDispatcher dispatcher1 = getServletContext().getRequestDispatcher(url);
        dispatcher1.forward(req, resp);

    }
}
