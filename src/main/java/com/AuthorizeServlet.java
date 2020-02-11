package com;

import com.DB.DB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/authorize")
public class AuthorizeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean result = DB.authorize(req.getParameter("login"), req.getParameter("password"));
        System.out.println("JJJJJ");
        String url;
        if (result)
            url = "/userPage.jsp";
        else url = "/Fail.jsp";
        RequestDispatcher dispatcher1 = getServletContext().getRequestDispatcher(url);
        dispatcher1.forward(req, resp);
    }

}
