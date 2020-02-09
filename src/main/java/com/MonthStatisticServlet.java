package com;

import com.DB.DB;
import com.model.UserStatistic;
import com.outInformation.ProductShow;
import com.outInformation.TwoProductShow;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/monthStatisticServlet")
public class MonthStatisticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        System.out.println(login);
        int thisMonth = DB.lastMonthUser(login);
        System.out.println(thisMonth);
        ArrayList<ProductShow> productShows = DB.getMonthStatistic(thisMonth, login);
        for(ProductShow productShow: productShows){
            System.out.println(productShow);
        }
        ArrayList<ProductShow> productShows1 = DB.getMonthStatistic(thisMonth, login);
        ArrayList<TwoProductShow> productShows2 = new ArrayList<>();
        for(int i = 0; i < productShows1.size(); i++){
            productShows2.add(new TwoProductShow(productShows.get(i).getName(),
                    productShows1.get(i).getAmountInStock(), productShows1.get(i).getAmountSold(),
                    productShows.get(i).getAmountInStock(), productShows.get(i).getAmountSold()));
        }
        req.setAttribute("list", productShows2);
        req.setAttribute("login", login);
        req.setAttribute("thisMonth", thisMonth);
        UserStatistic userStatistic = DB.findUserByLogin(login);
        req.setAttribute("userStoryName", userStatistic.getStoryName());
        req.setAttribute("userEmail", userStatistic.getEmail());
        String url = "/userPage.jsp";
        RequestDispatcher dispatcher1 = getServletContext().getRequestDispatcher(url);
        dispatcher1.forward(req,resp);
    }
}