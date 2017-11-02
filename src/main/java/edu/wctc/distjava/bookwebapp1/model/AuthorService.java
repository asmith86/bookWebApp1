/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.bookwebapp1.model;


import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


/**
 *
 * @author alexsmith
 */
@Stateless
public class AuthorService implements Serializable {
    
    @PersistenceContext(unitName = "book_PU")
    private EntityManager em; //dont refrence this property directly
    public AuthorService() {
         
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
   
    
    public List<Author> getAuthorList() throws Exception {
        
        String jpql = "select a from Author a";
        TypedQuery<Author> q = getEm().createQuery(jpql, Author.class);
        q.setMaxResults(500); //optional
        return  q.getResultList();
        

    }
    
    public void deleteAuthorRecord(String colName, int id) throws SQLException, ClassNotFoundException{
       
    }
    
    public void updateAuthorRecords(List<String> colNames, List<Object> colValues, 
            String whereCol, String operator, Object whereVal) throws ClassNotFoundException, SQLException{
       
    }
    
    public Author getUniqueAuthor(int id)
    throws SQLException, ClassNotFoundException {
        
       return null;
    }
    
    public void addAuthorRecord(List<String> colNames, List<Object> colValues)
            throws SQLException, ClassNotFoundException {
        
    }
    
}

            
    
    

    
 
