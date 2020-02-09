package com;


import com.DB.DB;
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

@WebServlet("/borderServlet")
public class BorderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<String> arrayList = new ArrayList<>();
        for(int i = 40; i < 50; i++){
            String k = "";
            for(int j = 0; j < i + 1; j++){
                k += "_";
            }
            arrayList.add(k);
        }
        req.setAttribute("list", arrayList);
        String url = "/New.jsp";
        RequestDispatcher dispatcher1 = getServletContext().getRequestDispatcher(url);
        dispatcher1.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<String> arrayList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            String k = "";
            for(int j = 0; j < i + 1; j++){
                k += "_";
            }
            arrayList.add(k);
        }
        req.setAttribute("list", arrayList);
        String url = "/New.jsp";
        RequestDispatcher dispatcher1 = getServletContext().getRequestDispatcher(url);
        dispatcher1.forward(req,resp);
    }
}
