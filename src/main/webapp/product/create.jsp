<%--Created by IntelliJ IDEA.--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Product</title>
</head>
<body>
    <div align="center">
        <h1>Edit Product</h1>
        <h3><a href="/products">Back to Product List</a></h3>
        <c:if test="${message != null}">
            <p style = 'color: red'>${message}</p>
        </c:if>
        <form method="post">
            <table>
            <tr>
                <td>Name</td>
                <td><input type = "text" name = "name" ></td>
            </tr>
            <tr>
                <td>Price</td>
                <td><input type = "text" name = "price" ></td>
            </tr>
            <tr>
                <td>Quantity</td>
                <td><input type = "number" name = "quantity" ></td>
            </tr>
            <tr>
                <td>Color</td>
                <td><input type = "text" name = "color" ></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><input type = "text" name = "description" ></td>
            </tr>
            <tr>
                <td>Category</td>
                <td><select name = "category">
                    <c:forEach items = "${categories}" var="category">
                    <option>${category.category}</option>
                    </c:forEach>
                </td>
            </tr>
            </table>
            <br/>
            <input type = "submit" value = "Create">
        </form>
    </div>
</body>
</html>
