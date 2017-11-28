/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.bookwebapp1.model;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author alexsmith
 */
@Stateless
public class AuthorService extends AbstractFacade<Author> {

    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorService() {
        super(Author.class);
    }
    
    
    public void addOrUpdateAuthor(String authorId, String name, String dateAdded) throws ParseException {

        Author author = null;

        if (authorId == null || authorId.isEmpty()) {
            // new record

            author = new Author();

        } else {
            //updated record
            author = new Author(new Integer(authorId));
        }

        author.setAuthorName(name);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateAdded);
        author.setDateAdded(date);
        
        

        getEntityManager().merge(author);

    }
    
    public void addNewAuthor(String name, String dateAdded) throws ParseException{
        Author author = new Author();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateAdded);
        author.setAuthorName(name);
        author.setDateAdded(date);
        this.create(author);
        
    }
    
    public void deleteAuthorRecord(String id) throws SQLException, ClassNotFoundException{
        Integer idInt = Integer.parseInt(id);
        String jpql = "delete from Author a where a.authorId = :id";
        Query q = getEntityManager().createQuery(jpql);
        q.setParameter("id", idInt);
        q.executeUpdate();
       
        
       
    }
    
    
    
    
}
