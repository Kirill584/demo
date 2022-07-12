package com.example.demo;
import java.sql.*;

public class DatabaseHandler{

        public Connection getconnection() throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1970_quotes",
                    "std_1970_quotes", "quotes123");

            return connection;
        }

        public void signUpUser(User user){
            String insert = "INSERT INTO" + " " + Const.USERS_TABLE + "(" + Const.USERS_LOGIN + "," + Const.USERS_PASSWORD + "," + Const.USERS_POST + "," + Const.USERS_COUNT_QUOTES + ")" + "VALUES(?,?,?,?)";
            try {
            PreparedStatement prSt = getconnection().prepareStatement(insert);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            prSt.setString(3, user.getPost());
            prSt.setString(4, "0");

            prSt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        public ResultSet getUser(User user){
            ResultSet ResSet = null;

            String select = "SELECT * FROM " + Const.USERS_TABLE + " WHERE " + Const.USERS_LOGIN + "=? AND " + Const.USERS_PASSWORD + "=? AND " + Const.USERS_POST + "=?";

            try {
                PreparedStatement prSt = getconnection().prepareStatement(select);
                prSt.setString(1, user.getLogin());
                prSt.setString(2, user.getPassword());
                prSt.setString(3, user.getPost());

                ResSet = prSt.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return ResSet;
        }

        public ResultSet getQuotes(Quotes quotes){
            ResultSet ResSet = null;

            String select = "SELECT * FROM " + Const.QUOTES_TABLE + " WHERE " + Const.QUOTES_QUOTES + "=? AND " + Const.QUOTES_TEACHER + "=? AND " + Const.QUOTES_SUBJECT + "=? AND " + Const.QUOTES_DATE + "=? AND " + Const.QUOTES_USER_ID + "=?";

            try {
                PreparedStatement prSt = getconnection().prepareStatement(select);
                prSt.setString(1, quotes.getQuotes());
                prSt.setString(2, quotes.getTeacher());
                prSt.setString(3, quotes.getSubject());
                prSt.setString(4, quotes.getDate());
                prSt.setString(5, quotes.getUser_id());

                ResSet = prSt.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }

            return ResSet;
        }
        public void signUpQuotes(Quotes quotes){
            String insert = "INSERT INTO" + " " + Const.QUOTES_TABLE + "(" + Const.QUOTES_QUOTES + "," + Const.QUOTES_TEACHER + "," + Const.QUOTES_SUBJECT + "," + Const.QUOTES_DATE + "," + Const.QUOTES_USER_ID +")" + "VALUES(?,?,?,?,?)";
            try {
                PreparedStatement prSt = getconnection().prepareStatement(insert);
                prSt.setString(1, quotes.getQuotes());
                prSt.setString(2, quotes.getTeacher());
                prSt.setString(3, quotes.getSubject());
                prSt.setString(4, quotes.getDate());
                prSt.setString(5, quotes.getUser_id());

                prSt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

}
