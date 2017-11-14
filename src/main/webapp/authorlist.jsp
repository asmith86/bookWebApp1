<%-- 
    Document   : authorlist
    Created on : Sep 19, 2017, 8:36:13 PM
    Author     : alexsmith
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Author List</title>
    </head>
    <body>
        <h1>Author List</h1>
        <form name="authorForm" id="authorForm" method="POST" action="authorController?action=addEditDelete">
        <table border="1">
            <c:forEach var="a" items="${authorList}">
                <tr>
                    <td>${a.authorId}</td>
                    <td>${a.authorName}</td>
                    <td>${a.getDateString()}</td>
                    <td><input type="checkbox" name="authorId" value="${a.authorId}"></td>
                </tr>
                
            </c:forEach>
        </table>
        <br>
        
            <input type="submit" value="addUpdate" name="submit">
            
            <input type="submit" value="Remove" name="submit">
      
        </form>
        
        <p>${errMessage}</p>
    </body>
</html>
