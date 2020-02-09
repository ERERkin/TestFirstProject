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
        String SQL = "insert into user_statistics (login, email, storyName, password) values (?,?,?,?)";
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
        String SQL = "insert into product_amounts (product_name, month_statistic_id,amountInStock, amountSold) values (?,?,?,?)";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setString(1,productAmount.getProductName());
            statement.setInt(2,productAmount.getMonthStatisticId());
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
        String SQL = "insert into products (code, name, price, url) values (?,?,?,?)";
        System.out.println("SQL!");
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setString(1,product.getCode());
            statement.setString(2,product.getName());
            statement.setDouble(3,product.getPrice());
            statement.setString(4,product.getUrl());
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean addMonthStatistic(MonthStatistic monthStatistic)  {
        String SQL = "insert into month_tatistics (user_login, day_count) values (?,?)";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setString(1, monthStatistic.getUserLogin());
            statement.setInt(2, monthStatistic.getMonthCount());
            statement.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public static ArrayList<ProductShow> getMonthStatistic(int monthCount, String login){
        ArrayList<ProductShow> productShows = new ArrayList<>();
        String SQL = "select p.name, pa.amountinstock , pa.amountsold from product_amounts pa\n" +
                "join products p on pa.product_name = p.name\n" +
                "join month_statistics ds on pa.month_statistic_id = ds.id\n" +
                "where ds.month_count = ? and ds.user_login = ?" +
                "order by p.name;";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setInt(1,monthCount);
            statement.setString(2,login);
            try(ResultSet rs = statement.executeQuery()) {
                while (rs.next()){
                    productShows.add(new ProductShow(rs.getString("name"), rs.getInt("amountinstock"), rs.getInt("amountsold")));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return productShows;
    }
    public static ArrayList<ProductShow> getPeriodStatisticForProduct(int a, int b, String login, String productName){
        ArrayList<ProductShow> productShows = new ArrayList<>();
        String SQL = "select p.name, pa.amountinstock , pa.amountsold from product_amounts pa " +
                "join products p on pa.product_name = p.name " +
                "join month_statistics ds on pa.month_statistic_id = ds.id " +
                "where ds.month_count >= ? and ds.month_count <= ? and ds.user_login = ? and p.name = ?;";
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
        } catch (SQLException e){
            e.printStackTrace();
        }
        return productShows;
    }
    public static int getMonthStatisticCount(int monthCount, String login){
        String SQL = "select * from month_statistics where day_count = ? and login = ?";
        int ans = 0;
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setInt(1, monthCount);
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
    public static ArrayList<ProductAmount> getThisMonthStatistic(int monthCount, String login){
        int id = getMonthStatisticCount(monthCount, login);
        ArrayList<ProductAmount> productAmounts = new ArrayList<>();
        String SQL = "select * product_amounts where day_statistic_id = ?";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setInt(1,id);
            try(ResultSet rs = statement.executeQuery()) {
                while (rs.next()){
                    productAmounts.add(new ProductAmount(rs.getInt("id"),rs.getString("product_name"),
                            rs.getInt("month_statistic_id")
                    , rs.getInt("amountInStock"), rs.getInt("amountSold")));
                }
            }
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return productAmounts;
    }
    public static MonthStatistic addNextMonth(MonthStatistic monthStatistic, ArrayList<ProductAmount> productAmounts) {
        monthStatistic.setMonthCount(monthStatistic.getMonthCount() + 1);
        addMonthStatistic(monthStatistic);
        int id = getMonthStatisticCount(monthStatistic.getMonthCount() , monthStatistic.getUserLogin());
        String SQL = "insert into product_amounts (product_name, day_statistic_id,amountInStock, amountSold) values (?,?,?,?)";
        for(int i = 0; i < productAmounts.size() - 1; i++){
            SQL += ",(?,?,?,?)";
        }
        SQL += ";";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(SQL);) {
            for(int i = 0; i < productAmounts.size(); i++){
                int k = i * 4;
                statement.setString(k + 1,productAmounts.get(i).getProductName());
                statement.setInt(k + 2,id);
                statement.setInt(k + 3,productAmounts.get(i).getAmountInStock());
                statement.setInt(k + 4,productAmounts.get(i).getAmountSold());
            }
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return monthStatistic;
    }
    public static ArrayList<Product> findProducts(String find){
        ArrayList<Product> products = new ArrayList<>();
        String SQL = "select * from products where name like ?";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setString(1,"%" + find + "%");
            try(ResultSet rs = statement.executeQuery()){
                while (rs.next()){
                    products.add(new Product(rs.getInt("id"),
                            rs.getString("code"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getString("url")));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }
    public static int lastMonthUser(String login){
        int ans = -1;
        String SQL = "select * from month_statistics where user_login = ?;";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setString(1,login);
            try(ResultSet rs = statement.executeQuery()){
                while (rs.next()){
                    ans = rs.getInt("month_count");
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return ans;
    }
    public static UserStatistic findUserByLogin(String login){
        UserStatistic userStatistic = null;
        String SQL = "select * from user_statistics where login = ?;";
        try(Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setString(1,login);
            try(ResultSet rs = statement.executeQuery()){
                rs.next();
                userStatistic = new UserStatistic(rs.getInt("id"),rs.getString("login"),
                        rs.getString("email"), rs.getString("storyName"),
                        rs.getString("password"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return userStatistic;
    }
}
