/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.bookwebapp1.model;


import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


/**
 *
 * @author alexsmith
 */
//Dont declare any methods final in this class! It will render the EJB useless.
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
        q.setMaxResults(500); //optional to limit result set
        return  q.getResultList();
        

    }
    
    public void deleteAuthorRecord(String id) throws SQLException, ClassNotFoundException{
        Integer idInt = Integer.parseInt(id);
        String jpql = "delete from Author a where a.authorId = :id";
        Query q = getEm().createQuery(jpql);
        q.setParameter("id", idInt);
        q.executeUpdate();
       
        
       
    }
    
    public void updateAuthorRecords(String name, String dateAdded) throws ParseException{
        String jpql = "update Author a set a.authorName = :name, a.dateAdded = :date " + 
                "where a.authorId = :id";
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateAdded);
       // Integer idInt = Integer.parseInt(id);
        Query q = getEm().createQuery(jpql);
       // q.setParameter("id", idInt);
        q.setParameter("name", name);
        q.setParameter("date", date);
        q.executeUpdate();
        
    }
    
    public void updateAuthorRecords(List<String> colNames, List<Object> colValues, 
            String whereCol, String operator, Object whereVal) throws ClassNotFoundException, SQLException{
        
        String jpql = "";
       
    }
    
    public Author getUniqueAuthor(String id)
    throws SQLException, ClassNotFoundException {
        Integer idInt = Integer.parseInt(id);
        Author author = getEm().find(Author.class, idInt);
        
       return author;
    }
    
    
    public void addAuthorRecord(String name, String dateAdded) throws ParseException{
        //Cannot insert using jqpl query
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateAdded);
        Author author = new Author();
        author.setAuthorName(name);
        author.setDateAdded(date);
        
       
        getEm().persist(author);
        
        
        
    }
    
    public void addAuthorRecord(String name){
        
        Author author = new Author();
        author.setAuthorName(name);
        
        
        getEm().persist(author);
        
        
    }
    
    
    
    
}

            
    
    

    
 
