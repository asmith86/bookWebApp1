///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package edu.wctc.distjava.bookwebapp1.model;
//
//
//import java.sql.*;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Vector;
//
///**
// *
// * @author alexsmith
// */
//public class DerbyDataAccess  { //implements dataAccess
//    private final int ALL_RECORDS = 0;
//    private Connection conn;
//    private Statement stmt;
//    private ResultSet rs;
//    private String driverClass;
//    private String url;
//    private String userName;
//    private String password;
//    
//    
//    public DerbyDataAccess(String driverClass, String url,
//            String userName, String password){
//        setDriverClass(driverClass);
//        setUrl(url);
//        setUserName(userName);
//        setPassword(password);
//    }
//
//    @Override
//    public String getDriverClass() {
//        return driverClass;
//    }
//
//    @Override
//    public final void setDriverClass(String driverClass) {
//        this.driverClass = driverClass;
//    }
//
//    @Override
//    public String getUrl() {
//        return url;
//    }
//
//    @Override
//    public final void setUrl(String url) {
//        this.url = url;
//    }
//
//    @Override
//    public String getUserName() {
//        return userName;
//    }
//
//    @Override
//    public final void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public final void setPassword(String password) {
//        this.password = password;
//    }
//    
//    
//    
//    @Override
//    public void openConnection() throws ClassNotFoundException, SQLException {
//
//        Class.forName(driverClass);
//     
//        conn =  DriverManager.getConnection(url, userName, password);
//        
//    }
//
//    @Override
//    public void closeConnection() throws SQLException {
//        if (conn != null) {
//            conn.close();
//        }
//
//    }
//    
//    /**
//     * Returns records from a table. Requires an open connection
//     * @param tableName
//     * @param maxRecords
//     * @return
//     * @throws SQLException 
//     */
//    @Override
//    public List<Map<String,Object>> getAllRecords(String tableName, int maxRecords) 
//            throws SQLException, ClassNotFoundException{
//        //using a vector for thread safety
//        List<Map<String,Object>> rawData = new Vector<>();
//        String sql = "";
//        
//        if(maxRecords > ALL_RECORDS){
//            sql = "SELECT TOP " + maxRecords + " * FROM" + tableName; 
//        } else {
//            sql = "select * from " + tableName;
//        }
//        
//        openConnection();
//        stmt = conn.createStatement();
//        rs = stmt.executeQuery(sql);
//        
//        ResultSetMetaData rsmd = rs.getMetaData();
//        int colCount = rsmd.getColumnCount();
//        Map<String,Object> record = null;
//        while(rs.next()){
//            record = new LinkedHashMap<>();
//            for(int colNum= 1; colNum <= colCount; colNum++){
//                record.put(rsmd.getColumnName(colNum), rs.getObject(colNum));
//            }
//            rawData.add(record);
//        }
//        closeConnection();
//        return rawData;
//    }
//    
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        DerbyDataAccess db = new DerbyDataAccess(
//                "org.apache.derby.jdbc.ClientDriver",
//                "jdbc:derby://localhost:1527/sample",
//                "app","app"
//        );
//        
//        List<Map<String,Object>> list = db.getAllRecords("Customer", 0);
//        
//        for(Map<String,Object> rec : list){
//            System.out.println(rec);
//        }
//    }
//
//    
//
//    @Override
//    public int deleteRecordById(String tableName, String keyCol, Object keyValue) throws SQLException, ClassNotFoundException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//
//}
//
