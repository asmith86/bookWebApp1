/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.bookwebapp1.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alexsmith
 */
public interface DataAccess {

    void closeConnection() throws SQLException;

    /**
     * Returns records from a table. Requires an open connection
     * @param tableName
     * @param maxRecords
     * @return
     * @throws SQLException
     */
    List<Map<String, Object>> getAllRecords(String tableName, int maxRecords) throws SQLException, ClassNotFoundException;
    
    Map<String, Object> getUniqueRecordById(String tableName, String keyCol, Object keyValue)
            throws SQLException, ClassNotFoundException;
    
    
    
    int deleteRecordById(String tableName, String keyCol, Object keyValue) throws SQLException, ClassNotFoundException;
    
    int createRecord(String tableName, List<String> colNames, List<Object> colValues) throws SQLException;
    
    int testUpdateRecord(String tableName, List<String> colNames, 
            List<Object> colValues, String whereCol, String operator,
            Object whereVal) throws SQLException;
  

  //  void openConnection() throws ClassNotFoundException, SQLException;
     void openConnection(String driverClass, String url, String userName, String password)
             throws ClassNotFoundException, SQLException;
     
     

   
    
    //make all these public abstract
    
}
