/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.bookwebapp1.model;

import com.mysql.jdbc.Connection;
import java.sql.*;
import java.sql.SQLException;

/**
 *
 * @author alexsmith
 */
public class MySqlDataAccess {
    private Connection conn;
    
    public void openConnection(String driverClass, String url,
            String userName, String password) throws ClassNotFoundException, SQLException {

        Class.forName(driverClass);
        conn = (Connection) DriverManager.getConnection(url, userName, password);
    }

    public void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }

    }
    
    


}

