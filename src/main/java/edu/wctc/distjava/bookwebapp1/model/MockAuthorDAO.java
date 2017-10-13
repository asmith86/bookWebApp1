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
       List<Author> list = null;

            list = Arrays.asList(new Author(1,"john doe", new Date()),
                    new Author(2, "Bob Smith", new Date())
            
            );

       
        

        return list;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MockAuthorDAO dao = new MockAuthorDAO();
        List<Author> list = dao.getListOfAuthors();
        for(Author a : list){
            System.out.println(a.getAuthorId() + ", " + a.getAuthorName() 
                    + ", " + a.getDateAdded() + "\n");
        }
    }

   

   

    @Override
    public int deleteAuthorRecordById(String keyCol, Object keyValue) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int testUpdateAuthorRecord(List<String> colNames, List<Object> colValues, String whereCol, String operator, Object whereVal) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Author getUniqueAuthorRecord(Object keyValue) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int addAuthorRecord(List<String> colNames, List<Object> colValues) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
