package com;

import com.DB.DB;
import com.model.Application;
import com.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        product.setName(req.getParameter("name"));
        product.setCode(req.getParameter("code"));
        product.setPrice(Double.parseDouble(req.getParameter("price")));
        product.setUrl(req.getParameter("url"));
        boolean result = DB.addProduct(product);
        System.out.println(result);
        String url;
        if(result){
            url = "/admin.jsp";
        }else{
            url = "/Fail.jsp";
        }
        RequestDispatcher dispatcher1 = getServletContext().getRequestDispatcher(url);
        dispatcher1.forward(req,resp);
    }
}
