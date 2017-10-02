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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Author> getAuthorList() throws SQLException, ClassNotFoundException {
        return authorDao.getListOfAuthors();
    
        
    }

    public IAuthorDAO getAuthorDao() {
        return authorDao;
    }

    public void setAuthorDao(IAuthorDAO authorDao) {
        this.authorDao = authorDao;
    }
    
    public static void main(String[] args) {
        //test code here
    }
    
}
