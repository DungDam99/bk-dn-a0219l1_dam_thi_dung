<%--
  Created by IntelliJ IDEA.
  User: DUNGMAMA
  Date: 3/13/2019
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Pdoduct Information</title>
    <style>
        .message{
            color: green;
        }
    </style>
</head>
<body>
<h1>Edit Pdoduct</h1>
<p>
    <c:if test="${requestScope['message'] != null}">
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<p>
    <a href="/products">Back to product list</a>
</p>
<p>
<form method="post">
    <fieldset>
        <legend>Product information</legend>
        <table>
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name" id="name" value="${requestScope['product'].getName()}"></td>
            </tr>
            <tr>
                <td>Price: </td>
                <td><input type="text" name="price" id="price" value="${requestScope['product'].getPrice()}"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Edit"></td>
            </tr>
        </table>
    </fieldset>
</form>
</p>
</body>
</html>
