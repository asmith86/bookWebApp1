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
public class BookService extends AbstractFacade<Book> {

    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookService() {
        super(Book.class);
    }

    public void addOrUpdateNewBook(String bookId, String title, String isbn, String authorId) {

        Book book = null;

        if (bookId == null || bookId.isEmpty()) {
            // new record

            book = new Book();

        } else {
            //updated record
            book = new Book(new Integer(bookId));
        }

        book.setTitle(title);
        book.setIsbn(isbn);
        Author author = getEntityManager().find(Author.class, new Integer(authorId));
        book.setAuthorId(author);

        getEntityManager().merge(book);

    }
    
    public void addNewBook(String title, String isbn, String authorId){
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        Author author = getEntityManager().find(Author.class, new Integer(authorId));
        book.setAuthorId(author);
        this.create(book);
        
    }
    
    public void deleteBook(String id) throws SQLException, ClassNotFoundException{
        Integer idInt = Integer.parseInt(id);
        String jpql = "delete from Book b where b.bookId = :id";
        Query q = getEntityManager().createQuery(jpql);
        q.setParameter("id", idInt);
        q.executeUpdate();
       
        
       
    }
    
    

}
