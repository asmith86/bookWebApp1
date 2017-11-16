/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.bookwebapp1.model;

import java.sql.SQLException;
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
    
    public void deleteAuthorRecord(String id) throws SQLException, ClassNotFoundException{
        Integer idInt = Integer.parseInt(id);
        String jpql = "delete from Author a where a.authorId = :id";
        Query q = getEntityManager().createQuery(jpql);
        q.setParameter("id", idInt);
        q.executeUpdate();
       
        
       
    }
    
    
    
    
}
