<%-- 
    Document   : editbook
    Created on : Nov 16, 2017, 8:55:55 PM
    Author     : alexsmith
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Add/Edit Books</h1>
        <form method="POST" action="bookController?action=addEditDelete">
            <table>

                <c:choose> 
                    <c:when test="${not empty book}">
                        <tr>
                            <td>ID</td>
                            <td><input type="text" value="${book.bookId}" name="bookId" readonly/></td>

                        </tr>
                    </c:when>
                </c:choose>
                <tr>
                    <td>Title</td>
                    <td><input type="text" value="${book.title}" name="title"></td>

                </tr>
                <tr>
                    <td>ISBN</td>
                    <td><input type="text" value="${book.isbn}" name="isbn"></td>
                </tr>
                <tr>
                    <td>Author Id</td>
                    <td><select name="authorId">
                            <c:forEach var="a" items="${authorList}">
                                
                                    
                                
                                <option value="${a.authorId}">${a.authorName}</option>
                            </c:forEach> 
                    
                        </select></td>
                </tr>




            </table> 
                <input type="submit" value="cancel" name="submit">
                <input type="submit" value="save" name="submit">
        </form>
    </body>
</html>

