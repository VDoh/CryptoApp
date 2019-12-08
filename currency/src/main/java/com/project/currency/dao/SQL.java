package com.project.currency.dao;
import java.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

public class SQL {
    public Connection connect() {
        // SQLite connection string

        String url = "jdbc:sqlite:currency.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("problem with sql connection");
        }
        return conn;
    }

    public String checkPassword(String nick) {

        String query = "SELECT password FROM user WHERE nick= ?";

        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nick);
            ResultSet rs = stmt.executeQuery();
            String password = rs.getString(1);
            return password;

        } catch (SQLException e) {
            return null;
        }
    }

}
