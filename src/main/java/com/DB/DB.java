package com.DB;

import com.model.*;
import com.outInformation.ProductShow;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;

public class DB {
    private static final String url = "jdbc:postgresql://localhost:5432/";
    private static final String user = "postgres";
    private static final String password = "postgres";
    @SneakyThrows
    public static Connection connect() {
        Class.forName("org.postgresql.Driver");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public static boolean addApplication(Application application) throws SQLException {
        String SQL = "insert into application (full_name,email,course,is_online)";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setString(1,application.getFullName());
            statement.setString(2,application.getEmail());
            statement.setString(3,application.getCourse());
            statement.setBoolean(4,application.getIsOnline());
            statement.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public static boolean addUserStatistic(UserStatistic userStatistic)  {
        String SQL = "insert into user_statistic (login, email, storyName, password) values (?,?,?,?)";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setString(1,userStatistic.getLogin());
            statement.setString(2,userStatistic.getEmail());
            statement.setString(3,userStatistic.getStoryName());
            statement.setString(4,userStatistic.getPassword());
            statement.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public static boolean addProductAmount(ProductAmount productAmount)  {
        String SQL = "insert into product_amounts (product_name, day_statistic_id,amountInStock, amountSold) values (?,?,?,?)";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setString(1,productAmount.getProductName());
            statement.setInt(2,productAmount.getDayStatisticId());
            statement.setInt(3,productAmount.getAmountInStock());
            statement.setInt(4,productAmount.getAmountSold());
            statement.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addProduct(Product product)  {
        String SQL = "insert into product (code, name, price, url) values (?,?,?,?)";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setString(1,product.getCode());
            statement.setString(2,product.getName());
            statement.setDouble(3,product.getPrice());
            statement.setString(4,product.getUrl());
            statement.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public static boolean addDayStatistic(DayStatistic dayStatistic)  {
        String SQL = "insert into dayStatistic (user_login, day_count) values (?,?)";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setString(1,dayStatistic.getUserLogin());
            statement.setInt(2,dayStatistic.getDayCount());
            statement.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public static ArrayList<ProductShow> getDayStatistic(int dayCount, String login){
        ArrayList<ProductShow> productShows = new ArrayList<>();
        String SQL = "select p.name, pa.amountinstock , pa.amountsold from product_amounts pa\n" +
                "join products p on pa.product_name = p.name\n" +
                "join day_statistics ds on pa.day_statistic_id = ds.id\n" +
                "where ds.day_count = ? and ds.user_login = ?;";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setInt(1,dayCount);
            statement.setString(2,login);
            try(ResultSet rs = statement.executeQuery()) {
                while (rs.next()){
                    productShows.add(new ProductShow(rs.getString("name"), rs.getInt("amountinstock"), rs.getInt("amountsold")));
                }
            }
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return productShows;
    }
    public static ArrayList<ProductShow> getPeriodStatisticForProduct(int a, int b, String login, String productName){
        ArrayList<ProductShow> productShows = new ArrayList<>();
        String SQL = "select p.name, pa.amountinstock , pa.amountsold from product_amounts pa\n" +
                "join products p on pa.product_name = p.name\n" +
                "join day_statistics ds on pa.day_statistic_id = ds.id\n" +
                "where ds.day_count >= ? and ds.day_count <= ? and ds.user_login = ? and p.name = ?;";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setInt(1,a);
            statement.setInt(2,b);
            statement.setString(3,login);
            statement.setString(4,productName);
            try(ResultSet rs = statement.executeQuery()) {
                while (rs.next()){
                    productShows.add(new ProductShow(rs.getString("name"), rs.getInt("amountinstock"), rs.getInt("amountsold")));
                }
            }
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return productShows;
    }
    public static int getDayStatisticCount(int dayCount, String login){
        String SQL = "select * from day_statistic where day_count = ? and login = ?";
        int ans = 0;
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setInt(1, dayCount);
            statement.setString(1,login);
            try(ResultSet rs = statement.executeQuery()) {
                rs.next();
                ans = rs.getInt("id");
            }
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return ans;
    }
    public static ArrayList<ProductAmount> getThisDayStatistic(int dayCount, String login){
        int id = getDayStatisticCount(dayCount, login);
        ArrayList<ProductAmount> productAmounts = new ArrayList<>();
        String SQL = "select * product_amount where day_statistic_id = ?";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setInt(1,id);
            try(ResultSet rs = statement.executeQuery()) {
                while (rs.next()){
                    productAmounts.add(new ProductAmount(rs.getString("product_name"), rs.getInt("day_statistic_id")
                    , rs.getInt("amountInStock"), rs.getInt("amountSold")));
                }
            }
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return productAmounts;
    }
}
