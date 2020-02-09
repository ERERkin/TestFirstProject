package com;
import com.DB.DB;
import com.model.Product;
import com.model.ProductAmount;
import com.outInformation.ProductShow;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/addProductAmountServlet")
public class AddProductAmountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String add = req.getParameter("add");
        int month = Integer.parseInt(req.getParameter("month"));
        String login = req.getParameter("login");
        DB.addProductAmount(new ProductAmount(1, add, month, 0, 0));
        req.setAttribute("login", login);
        String url = "/monthStatisticServlet";
        RequestDispatcher dispatcher1 = getServletContext().getRequestDispatcher(url);
        dispatcher1.forward(req,resp);
    }
}
