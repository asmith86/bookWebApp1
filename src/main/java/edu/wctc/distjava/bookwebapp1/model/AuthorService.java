/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.bookwebapp1.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author alexsmith
 */
public class AuthorService {
    
    public List<Author> getAuthorList() {
        List<Author> authors = new ArrayList<>();
        return Arrays.asList(
                new Author(1, "Mark Twain", new Date()),
                new Author(1, "Stephen King", new Date()),
                new Author(1, "George Orwell", new Date())
                                
        );
        
    }
    
}
