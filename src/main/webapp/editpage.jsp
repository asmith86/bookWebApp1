<%-- 
    Document   : editpage
    Created on : Oct 15, 2017, 9:12:12 PM
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
        <form method="POST" action="authorController?action=addEditDelete">
            <table>

                <c:choose> <!-- Conditionally display Author ID -->
                    <c:when test="${not empty author}">
                        <tr>
                            <td>ID</td>
                            <td><input type="text" value="${author.authorId}" name="authorId" readonly/></td>

                        </tr>
                    </c:when>
                </c:choose>
                <tr>
                    <td>Author Name</td>
                    <td><input type="text" value="${author.authorName}" name="authorName"></td>

                </tr>
                <tr>
                    <td>Date Added</td>
                    <td><input type="text" value="${author.dateAdded}" name="dateAdded"></td>
                </tr>




            </table> 
                <input type="submit" value="cancel" name="submit">
                <input type="submit" value="save" name="submit">
        </form>
    </body>
</html>
