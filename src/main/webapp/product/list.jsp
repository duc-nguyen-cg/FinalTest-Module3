<%--Created by IntelliJ IDEA.--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Product List</title>
</head>
<body>
<div align="center">
    <h1> Product List </h1>
    <h3><a href = "/products?action=create">Add New Product </a></h3>
    <form >
        <input type = "text" name = "search" placeholder="Search By Name">
        <input type = "submit" value = "Search">
    </form>

    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Color</th>
            <th>Description</th>
            <th>Category</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items = "${products}" var = "product">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.quantity}</td>
                <td>${product.color}</td>
                <td>${product.description}</td>
                <td>${product.category}</td>
                <td><a href="/products?action=edit&id=${product.id}">Edit</a></td>
                <td><a href="/products?action=delete&id=${product.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>


</div>
</body>
</html>
