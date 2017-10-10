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
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringJoiner;

/**
 *
 * @author alexsmith
 */
public class MySqlDataAccess implements DataAccess {
    private boolean DEBUG = true;
    private final int ALL_RECORDS = 0;
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;
//   
    public MySqlDataAccess(){
        
    }


    
    
    
    public void openConnection(String driverClass, String url,
            String userName, String password) throws ClassNotFoundException, SQLException {

        Class.forName(driverClass);
        
        conn =  DriverManager.getConnection(url, userName, password);
       
       
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
    @Override
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
       // closeConnection();
        return rawData;
    }
   
 
    
    @Override
    //use prepared statements in this class!
    public int deleteRecordById(String tableName, String keyCol, Object keyValue) throws ClassNotFoundException, SQLException{
        String sql = "delete * from " + tableName + " where " +
                    keyCol + "= ";
        
        if(keyValue instanceof String){
            
            sql += "'" + keyValue.toString() + "'";
        } else if(keyValue instanceof Long){
            sql += Long.parseLong(keyValue.toString());
        }
      
        stmt = conn.createStatement();
        int recsDeleted = stmt.executeUpdate(sql);
       
        
        return recsDeleted;
        
    }
    
    @Override
    public int createRecord(String tableName, List<String> colNames, List<Object> colValues) 
    throws SQLException{
        String sql = "INSERT INTO " + tableName + " ";
        StringJoiner sj = new StringJoiner(", ", "(", ")");
        
        for(String col: colNames){
            sj.add(col);
        }
        
        
        
        sql += sj.toString();
        sql += " VALUES ";
        
        
        
        sj = new StringJoiner(", ", "(", ")"); // delimeter, prefix, suffix
        for(Object value : colValues){
            sj.add("?");
        }
        
        
        
        sql += sj.toString();
        
        if(DEBUG){
            System.out.println(sql);
        }
        pstmt = conn.prepareStatement(sql);
        for(int i=1; i <= colValues.size(); i++){
            pstmt.setObject(i, colValues.get(i-1));
            
            
        }
        return pstmt.executeUpdate();
    }
    
    public int testUpdateRecord(String tableName, List<String> colNames, 
            List<Object> colValues, String whereCol, String operator,
            Object whereVal) throws SQLException{
        
        String sql ="UPDATE " + tableName + " SET ";
        StringJoiner sj = new StringJoiner(", ");
        
        for(String col : colNames){
            sj.add(col + " = " + "?");
        }
        
        sql += sj.toString();
        
        sql += " WHERE " + whereCol + " " + operator + " ?";
        
        if(DEBUG){
            System.out.println(sql);
        }
        
        pstmt = conn.prepareStatement(sql);
        
        for(int i = 1; i <= colValues.size(); i++){
            pstmt.setObject(i, colValues.get(i - 1));
        }
        
        pstmt.setObject(colValues.size() + 1, whereVal); //value after colValues is the conditional
        
        
        
        return pstmt.executeUpdate();
    }
    
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MySqlDataAccess db = new MySqlDataAccess();
        
       
        
        db.openConnection("com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book",
                "root", "admin");
        
        db.testUpdateRecord("author", 
                Arrays.asList("author_name", "date_added"), 
                Arrays.asList("Bob Jones", "2010-02-11"), 
                "author_id", "=", "1");
        
        db.closeConnection();
        
//        List test = Arrays.asList("one", "two", "three", "four", "five");
//        
//        for(int i = 1; i<=test.size(); i++){
//            System.out.println(test.get(i - 1));
//        }
//        System.out.println(test.size());
        
        
        
        

        
    
//        db.createRecord("author", 
//                Arrays.asList("author_name", "date_added"),
//                Arrays.asList("Bob Jones", "2010-02-11" ));
//        
//
//             
//        
//        List<Map<String,Object>> list = db.getAllRecords("author", 0);
//        
//        for(Map<String,Object> rec : list){
//            System.out.println(rec);
//        }
//        db.deleteRecordById("author", "author_id", 1);
       
    }


}

