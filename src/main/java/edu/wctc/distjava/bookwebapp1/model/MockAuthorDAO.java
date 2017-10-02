/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.bookwebapp1.model;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;


/**
 *
 * @author alexsmith
 */
public class MockAuthorDAO implements IAuthorDAO {
    
    private DataAccess db;
    
    public MockAuthorDAO(){
    }

    @Override
    public List<Author> getListOfAuthors() throws SQLException, ClassNotFoundException {
        List<Author> list = new Vector<>();
        List<Map<String, Object>> rawData
                = db.getAllRecords("author", 0);

        Author author = null;

        for (Map<String, Object> rec : rawData) {
            list = Arrays.asList(new Author(1,"john doe", new Date()));

       
        }

        return list;
    }
    
 

}
