/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.bookwebapp1.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alexsmith
 */
public class AuthorService {
    private IAuthorDAO authorDao;
    
    public AuthorService(IAuthorDAO authorDao){
        setAuthorDao(authorDao);
        
    }

    public AuthorService() {
         
    }
    
    public List<Author> getAuthorList() throws SQLException, ClassNotFoundException {
        return authorDao.getListOfAuthors();

    }
    
    public void deleteAuthorRecord(String colName, int id) throws SQLException, ClassNotFoundException{
        authorDao.deleteAuthorRecordById(colName, id);
    }
    
    public void updateAuthorRecords(List<String> colNames, List<Object> colValues, 
            String whereCol, String operator, Object whereVal) throws ClassNotFoundException, SQLException{
        authorDao.testUpdateAuthorRecord(colNames, colValues, whereCol, operator, whereVal);
    }
    
    public Author getUniqueAuthor(int id)
    throws SQLException, ClassNotFoundException {
        
       return authorDao.getUniqueAuthorRecord(id);
    }
    
    public void addAuthorRecord(List<String> colNames, List<Object> colValues)
            throws SQLException, ClassNotFoundException {
        authorDao.addAuthorRecord(colNames, colValues);
    }
            
    
    

    public IAuthorDAO getAuthorDao() {
        return authorDao;
    }

    public void setAuthorDao(IAuthorDAO authorDao) {
        this.authorDao = authorDao;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        IAuthorDAO dao = new AuthorDAO(
                "com.mysql.jdbc.Driver",
                "jdbc:msql://localhost:3306/book",
                "root", "admin",
                new MySqlDataAccess()
                      
                
        );
        AuthorService authorService = 
                new AuthorService(dao);
        
        List<Author> list = authorService.getAuthorList();
        
        for(Author a : list){
            System.out.println(a.getAuthorId() + ", " + a.getAuthorName() 
                    + ", " + a.getDateAdded() + "\n");
        }
        
       dao.deleteAuthorRecordById("author_id", 1);
        
    }
    
}
