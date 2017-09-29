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
public class AuthorDAO {
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

}
