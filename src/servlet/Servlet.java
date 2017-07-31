package servlet;

import Bean.UserBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Servlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        handleHttp(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        handleHttp(request,response);
    }

    private void handleHttp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter printWriter =response.getWriter();
        if (null!=username &&  !"".equals(username)){
            printWriter.println(username);
        }
        if (null!=password &&  !"".equals(password)){
            printWriter.println(password);
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test?Unicode=true&characterEncoding=utf-8";
            String sqlusername = "root";
            String sqlpassword = "";
            Connection connection = DriverManager.getConnection(url, sqlusername,sqlpassword);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM login WHERE  username = '"+username+"';";
            ResultSet rs = null;
            rs = statement.executeQuery(sql);
            int x = statement.executeUpdate(sql);
            //rs.last();
            List<UserBean> list = new ArrayList<>();
            while (rs.next()) {
                UserBean user = new UserBean();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                list.add(user);
            }


            rs.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
