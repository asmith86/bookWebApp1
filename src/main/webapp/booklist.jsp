<%-- 
    Document   : booklist
    Created on : Nov 15, 2017, 9:10:54 PM
    Author     : alexsmith
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book List</title>
    </head>
    <body>
        <h1>Book List</h1>
        <form name="bookForm" id="bookForm" method="POST" action="bookController?action=addEditDelete">
        <table border="1">
            <c:forEach var="b" items="${bookList}">
                <tr>
                    <td>${b.bookId}</td>
                    <td>${b.title}</td>
                    <td>${b.isbn}</td>
                    <td>${b.authorId.authorId}</td>
                    <td><input type="checkbox" name="bookId" value="${b.bookId}"></td>
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
