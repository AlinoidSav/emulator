package org.example.rest.service;

import org.example.rest.Model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.sql.*;

@Component
public class DatabaseConnector {
    public static final String DB_URL = "jdbc:postgresql://192.168.0.6:5432/db";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "postgrespassword";

    public User findUserByLogin(String login) throws SQLException {
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        String sql = "SELECT u.login, u.password, u.date, e.email " +
                "FROM users u JOIN emails e ON u.login = e.login " +
                "WHERE u.login = '" + login + "'";
        try {
            con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            rs.next();
            return new User(
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("date"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public int insertUser(User user){
        String sql = "INSERT INTO users(login, password) VALUES (?, ?);\n" +
                "INSERT INTO emails(login, email) VALUES (?, ?)";
        try (Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getEmail());
            return ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
