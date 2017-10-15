/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.bookwebapp1.model;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/*
* Remember to document classes
*/


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
  
    
// add method that creates an author record by taking an author.    
//    public int addAuthor(Author author){
//        return 0;
//    }
    
    
    
    

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
        db.openConnection(driverClass, url, userName, password);
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
        db.closeConnection();

        return list;
    }
    
    public final Author getUniqueAuthorRecord(Object keyValue)
    throws SQLException, ClassNotFoundException{
        db.openConnection(driverClass, url, userName, password);
        Map<String, Object> rec = db.getUniqueRecordById("author", 
                "author_id", keyValue);
        
        Author author = new Author();
        
        Object objRecId = rec.get("author_id");
        Integer recId = objRecId == null ? 0 : Integer.parseInt(objRecId.toString());
        author.setAuthorId(recId);
        
        Object objName = rec.get("author_name");
        String authorName = objName == null ? "" : objName.toString();
        author.setAuthorName(authorName);
        
        Object objDate = rec.get("date_added");
        Date authDate = objDate == null ? null : (Date) objDate;
        author.setDateAdded(authDate);
        
        return author;
    }
    
    @Override
    public int addAuthorRecord(List<String> colNames, List<Object> colValues)
    throws SQLException, ClassNotFoundException {
        db.openConnection(driverClass, url, userName, password);
        int recsAdded = db.createRecord("author", colNames, colValues);
        db.closeConnection();
        return recsAdded;
    }
    
   
    
     @Override
    public int deleteAuthorRecordById(String keyCol, Object keyValue) throws SQLException, ClassNotFoundException {
        db.openConnection(driverClass, url, userName, password);
        int recsDeleted = db.deleteRecordById("author", keyCol, keyValue);
        db.closeConnection();

        return recsDeleted;
    }
    
    @Override
    public int updateAuthorRecord(List<String> colNames, List<Object> colValues,
            String whereCol, String operator, Object whereVal) throws ClassNotFoundException, SQLException{
        
        db.openConnection(driverClass, url, userName, password);
        int recsUpdated = db.updateRecord("author", colNames, colValues, whereCol, operator, whereVal);
        db.closeConnection();
        
        return recsUpdated;
        
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //retrieve, delete, update, insert works -- No issues.
        AuthorDAO dao = new AuthorDAO(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/book",
                "root", "admin",
                new MySqlDataAccess()
                     
        );
        dao.addAuthorRecord(Arrays.asList("author_name", "date_added"),
                Arrays.asList("Sara Smith", "2005-12-04"));
        
        
        
        
        
//        dao.updateAuthorRecord(Arrays.asList("author_name", "date_added"), 
//                Arrays.asList("Rick Astley", "2014-09-06"), 
//                "author_id", "=", 1);
        
//        List<Author> list = dao.getListOfAuthors();
//        for(Author a : list){
//            System.out.println(a.getAuthorId() + ", " + a.getAuthorName() 
//                    + ", " + a.getDateAdded() + "\n");
//        }
//        
//        System.out.println("");
//        
//        Author unique = dao.getUniqueAuthorRecord(2);
//        System.out.println(unique.getAuthorId() + " " + 
//                unique.getAuthorName() + " " + unique.getDateAdded());



        
       
        
       //dao.deleteAuthorRecordById("author_id", 6);
       
       
       
       
       
    }

   

}
