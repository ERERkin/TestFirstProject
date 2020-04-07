package com;

import com.model.Application;
import lombok.SneakyThrows;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@WebServlet("/boo")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*Application application = new Application();
        application.setFullName(req.getParameter("name"));
        application.setEmail(req.getParameter("email"));
        application.setCourse(req.getParameter("course"));
        application.setIsOnline("on".equals(req.getParameter("online")));
        boolean result = Application.addApplicationToBD(application);
        System.out.println(result);
        req.setAttribute("result", result ? "Success" : "Fail");
        //req.setAttribute("application",application);
        String url = "/New.jsp";
        RequestDispatcher dispatcher1 = getServletContext().getRequestDispatcher(url);
        dispatcher1.forward(req,resp);*/
        PrintWriter writer = resp.getWriter();
        writer.print("<h1>FFFF</h1>");
        int [][] a = {{1,2},{1,2}};
    }
}
