/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.bookwebapp1.model;

import edu.wctc.distjava.bookwebapp1.repository.AuthorRepository;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
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
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepo;
   

   

    public AuthorService() {
        
    }
    
    public List<Author> findAll(){
        return authorRepo.findAll();
    }
    
    public Author findById(String id){
       return authorRepo.findOne(Integer.parseInt(id));
    }
    
    public void addAuthor(String name){
        Date dateAdded = new Date();
        Author author = new Author();
        author.setAuthorName(name);
        author.setDateAdded(dateAdded);
        author.setBookSet(new HashSet());
        
        authorRepo.save(author);
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
        
        

        authorRepo.save(author);

    }
 
    //assume date added is not editable
    public void updateAuthor(String id, String name){
        Author author = findById(id);
        author.setAuthorName(name);
        authorRepo.save(author);
    }
    
    
    
    public void deleteAuthorRecord(String id) throws SQLException, ClassNotFoundException{
        Integer idInt = Integer.parseInt(id);
        
        authorRepo.delete(idInt);
            
       
    }
    
    
    
    
    
    
}
