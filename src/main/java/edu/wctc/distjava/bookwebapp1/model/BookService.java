/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.bookwebapp1.model;

import edu.wctc.distjava.bookwebapp1.repository.AuthorRepository;
import edu.wctc.distjava.bookwebapp1.repository.BookRepository;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexsmith
 */
@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private AuthorRepository authorRepo;

    public BookService(){
        
    }
    
    public List<Book> findAll(){
        return bookRepo.findAll();
    }
    
    public Book findById(String id){
       return bookRepo.findOne(Integer.parseInt(id));
    }
    
    
    
    //authorId refers to an author object
    public void addNewBook(String title, String isbn, String authorId){
        int id = Integer.parseInt(authorId);
        Author author = authorRepo.findOne(id);
        Book book = new Book();
        book.setAuthorId(author);
        book.setIsbn(isbn);
        book.setTitle(title);
        
        bookRepo.save(book);
        
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
        Author author = authorRepo.findOne(Integer.parseInt(authorId));
        //Author author = getEntityManager().find(Author.class, new Integer(authorId));
        book.setAuthorId(author);

        bookRepo.save(book);

    }
    
//    public void addNewBook(String title, String isbn, String authorId){
//        Book book = new Book();
//        book.setTitle(title);
//        book.setIsbn(isbn);
//        Author author = getEntityManager().find(Author.class, new Integer(authorId));
//        book.setAuthorId(author);
//        this.create(book);
//        
//    }
    
    public void deleteBook(String id) throws SQLException, ClassNotFoundException{
        Integer idInt = Integer.parseInt(id);
        
        bookRepo.delete(idInt);
        
       
    }
    
    

}
