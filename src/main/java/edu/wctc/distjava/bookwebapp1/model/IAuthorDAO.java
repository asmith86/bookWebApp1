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
public interface IAuthorDAO {

    List<Author> getListOfAuthors() throws SQLException, ClassNotFoundException;
    
    
    int deleteAuthorRecordById(String keyCol, Object keyValue) throws SQLException, ClassNotFoundException;
    
    int testUpdateAuthorRecord(List<String> colNames, List<Object> colValues,
            String whereCol, String operator, Object whereVal) throws ClassNotFoundException, SQLException;
    
    Author getUniqueAuthorRecord(Object keyValue) throws SQLException, ClassNotFoundException;
    
}
