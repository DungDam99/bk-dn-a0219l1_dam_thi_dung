<%--
  Created by IntelliJ IDEA.
  User: DUNGMAMA
  Date: 3/13/2019
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Deleting product</title>
</head>
<body>
<h1>Delete product</h1>
<p>
    <a href="/products">Back to product list</a>
</p>
<form method="post">
    <fieldset>
        <legend>Product Information</legend>
        <table>
            <tr>
                <td>Name : </td>
                <td>${requestScope['product'].getName()}</td>
            </tr>
            <tr>
                <td>Price : </td>
                <td>${requestScope['product'].getPrice()}</td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Delete"></td>
            </tr>
        </table>
    </fieldset>

</form>
</body>
</html>
