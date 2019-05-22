<%--
  Created by IntelliJ IDEA.
  User: DUNGMAMA
  Date: 3/13/2019
  Time: 3:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View roduct</title>
</head>
<body>
<h1>Product details</h1>
<p>
    <a href="/products">Back to product list</a>
</p>
<table>
    <tr>
        <td>Name : </td>
        <td>${requestScope['product'].getName()}</td>
    </tr>
    <tr>
        <td>Price : </td>
        <td>${requestScope['product'].getPrice()}</td>
    </tr>
</table>
</body>
</html>
