<%-- 
    Document   : editbook
    Created on : Nov 16, 2017, 8:55:55 PM
    Author     : alexsmith
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Add/Edit Authors</h1>
        <form method="POST" action="bookController?action=addEditDelete">
            <table>

                <c:choose> <!-- Conditionally display Author ID -->
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
                    <td><input type="text" value="${book.authorId.authorId}" name="authorId" readonly/></td>
                </tr>




            </table> 
                <input type="submit" value="cancel" name="submit">
                <input type="submit" value="save" name="submit">
        </form>
    </body>
</html>

