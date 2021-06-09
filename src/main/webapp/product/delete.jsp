<%--Created by IntelliJ IDEA.--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Product</title>
</head>
<body>
    <div align="center">
        <h1>Are you sure?</h1>
        <h3><a href="/products">Back to Product List</a></h3>
        <form method="post">
            <p>Name : ${product.name}</p>
            <p>Price: ${product.price}</p>
            <p>Quantity: ${product.quantity}</p>
            <p>Color: ${product.color}</p>
            <p>Description: ${product.description}</p>
            <p>Category: ${product.category}</p>
            <input type="submit" value="Delete">
        </form>
    </div>
</body>
</html>
