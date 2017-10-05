/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.bookwebapp1.model;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author alexsmith
 */
public class AuthorDAO implements IAuthorDAO {
     
    private String driverClass;
    private String url;
    private String userName;
    private String password;
    private DataAccess db;
    
    public AuthorDAO(String driverClass, String url,
            String userName, String password, DataAccess db){
        setDriverClass(driverClass);
        setUrl(url);
        setUserName(userName);
        setPassword(password);
        setDb(db);
    }

    public DataAccess getDb() {
        return db;
    }

    public void setDb(DataAccess db) {
        this.db = db;
    }
    
    

    public String getDriverClass() {
        return driverClass;
    }

    public final void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public final void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public final void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public final void setPassword(String password) {
        this.password = password;
    }

    @Override
    public List<Author> getListOfAuthors() throws SQLException, ClassNotFoundException {
        List<Author> list = new Vector<>();
        List<Map<String, Object>> rawData
                = db.getAllRecords("author", 0);

        Author author = null;

        for (Map<String, Object> rec : rawData) {

            author = new Author();
            Object objRecId = rec.get("author_id");
            Integer recId = objRecId == null ? 0 : Integer.parseInt(objRecId.toString());
            author.setAuthorId(recId);

            Object objName = rec.get("author_name");
            String authorName = objName == null ? "" : objName.toString();
            author.setAuthorName(authorName);

            Object objDate = rec.get("date_added");
            Date objdate = objDate == null ? null : (Date) objDate;
            author.setDateAdded(objdate);

            list.add(author);

        }

        return list;
    }
    
   
    
     @Override
    public int deleteAuthorRecordById(String keyCol, Object keyValue) throws SQLException, ClassNotFoundException {
        int recsDeleted = db.deleteRecordById("author", keyCol, keyValue);

        return recsDeleted;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AuthorDAO dao = new AuthorDAO(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book",
                "root", "admin",
                new MySqlDataAccess(
                        "com.mysql.jdbc.Driver",
                        "jdbc:mysql://localhost:3306/book",
                        "root", "admin"
                )
        );
        
        List<Author> list = dao.getListOfAuthors();
        for(Author a : list){
            System.out.println(a.getAuthorId() + ", " + a.getAuthorName() 
                    + ", " + a.getDateAdded() + "\n");
        }
        
       dao.deleteAuthorRecordById("author_id", 1);
    }

   

}
