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
        <table border="1">
            <c:forEach var="a" items="${authorList}">
                <tr>
                    <td>${a.authorId}</td>
                    <td>${a.authorName}</td>
                 
                    <td></td>
                    <td>${a.dateAdded}</td>
                </tr>
                <p>${a.authorName}</p>
            </c:forEach>
        </table>
    </body>
</html>
