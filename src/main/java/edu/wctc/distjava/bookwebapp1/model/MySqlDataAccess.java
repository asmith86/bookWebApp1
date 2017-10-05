/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.bookwebapp1.model;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author alexsmith
 */
public class MySqlDataAccess implements DataAccess {
    private final int ALL_RECORDS = 0;
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String driverClass;
    private String url;
    private String userName;
    private String password;
    
    
    public MySqlDataAccess(String driverClass, String url,
            String userName, String password){
        setDriverClass(driverClass);
        setUrl(url);
        setUserName(userName);
        setPassword(password);
    }

    public String getDriverClass() {
        return driverClass;
    }

    public final void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public final void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public final void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public final void setPassword(String password) {
        this.password = password;
    }
    
    
    
    public void openConnection() throws ClassNotFoundException, SQLException {

        Class.forName(driverClass);
        //cannot connect! complains of no suitable driver
        conn =  DriverManager.getConnection(url, userName, password);
       
       //using this as a temporary solution because for some reason I don't get data
       //when I pass the values in
      // conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "admin");
    }

    public void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }

    }
    
    /**
     * Returns records from a table. Requires an open connection
     * @param tableName
     * @param maxRecords
     * @return
     * @throws SQLException 
     */
    public List<Map<String,Object>> getAllRecords(String tableName, int maxRecords) 
            throws SQLException, ClassNotFoundException{
        //using a vector for thread safety
        List<Map<String,Object>> rawData = new Vector<>();
        String sql = "";
        
        if(maxRecords > ALL_RECORDS){
            sql = "SELECT * FROM" + tableName + " limit " + maxRecords;
        } else {
            sql = "select * from " + tableName;
        }
        
        openConnection();
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        Map<String,Object> record = null;
        while(rs.next()){
            record = new LinkedHashMap<>();
            for(int colNum= 1; colNum <= colCount; colNum++){
                record.put(rsmd.getColumnName(colNum), rs.getObject(colNum));
            }
            rawData.add(record);
        }
        closeConnection();
        return rawData;
    }
   
    @Override
    public void deleteRecordbyId(String tableName, String colName, int id) throws ClassNotFoundException, SQLException{
        String sql = "DELETE * FROM " + tableName + 
                "WHERE " + colName + "= " + id;
        openConnection();
        stmt = conn.createStatement();
        stmt.executeQuery(sql);
        
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MySqlDataAccess db = new MySqlDataAccess(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book",
                "root","admin"
        );
        
        List<Map<String,Object>> list = db.getAllRecords("author", 0);
        
        for(Map<String,Object> rec : list){
            System.out.println(rec);
        }
        
      //  db.deleteRecordbyId("author", "author_id", 1);
    }


}

