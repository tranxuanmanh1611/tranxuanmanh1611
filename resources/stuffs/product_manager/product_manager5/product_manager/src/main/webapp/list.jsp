<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 03/05/2023
  Time: 4:07 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <table>
        <tr>
            <td>
                <a href="products"><button><h1>ProductList</h1></button></a>
            </td>
            <td>
                <a href="products?action=create"><button class="create">Create</button></a>
            </td>
            <td colspan="2">
                <form method="post" action="products?action=search">
                    <input name="searchValue" type="text" placeholder="what do you want to find?">
                    <button type="submit" class="create">Go</button>
                </form>
            </td>
        </tr>

        <!-- create new product-->
        <c:if test="${requestScope['create']!=null}">
            <form action="" method="post">
                <td><input name="name" type="text" placeholder="product's name"></td>
                <td>
                    <select name="brand">
                        <c:forEach items="${requestScope['brandList']}" var="brand">
                            <option value="${brand.getId()}">${brand.getName()}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <select name="category">
                        <c:forEach items="${requestScope['categoryList']}" var="category">
                            <option value="${category.getId()}">${category.getName()}</option>
                        </c:forEach>
                    </select>
                </td>
                <td><input name="description" type="text" placeholder="product's description"></td>
                <td><input name="price" type="number" placeholder="product's price"></td>
                <td><input name="img" type="text" placeholder="product's image link"></td>
                <td>
                    <button type="submit" onclick="return createConfirm()" class="create">Create</button>
            </form>
                    <a href="products" onclick="return cancelConfirm()"><button class="cancel">Cancel</button></a>
                </td>
                </c:if>


        <tr>
            <th>Name</th>
            <th>Brand</th>
            <th>Category</th>
            <th>Description</th>
            <th>Price</th>
            <th>Image</th>
            <th>Operations</th>
        </tr>

        <c:forEach items="${requestScope['productList']}" var="product">
            <c:choose>
                <c:when test="${requestScope['edit']==product.getId()}">
                    <form action="" method="post">
                        <td><input name="name" type="text" placeholder="old: ${product.getName()}"></td>
                        <td>
                            <select name="brand">
                                <c:forEach items="${requestScope['brandList']}" var="brand">
                                    <option value="${brand.getId()}">${brand.getName()}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select name="category">
                                <c:forEach items="${requestScope['categoryList']}" var="category">
                                    <option value="${category.getId()}">${category.getName()}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td><input name="description" type="text" placeholder="old: ${product.getDescription()}"></td>
                        <td><input name="price" type="number" placeholder="old: ${product.getPrice()}"></td>
                        <td><input name="img" type="text" placeholder="product's image link"></td>
                        <td>
                            <button type="submit" onclick="return editConfirm()" class="edit">Edit</button>
                    </form>
                    <a href="products" onclick="return cancelConfirm()"><button class="cancel">Cancel</button></a>
                    </td>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td>${product.getName()}</td>
                        <td>${product.getBrand()}</td>
                        <td>${product.getCategory()}</td>
                        <td>${product.getDescription()}</td>
                        <td>${product.getPrice()}</td>
                        <td><img src="${product.getImg()}" alt="product's image"></td>
                        <td>
                            <a href="products?action=delete&id=${product.getId()}" onclick=" return deleteConfirm()"><button class="delete">Delete</button></a>
                            <a href="products?action=edit&id=${product.getId()}"><button class="edit">Edit</button></a>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>

        </c:forEach>
    </table>


    <script>
        function createConfirm() {
            return window.confirm("Are you sure you want to create?")
        }
        function cancelConfirm() {
            return window.confirm("Are you sure you want to cancel?")
        }
        function editConfirm() {
            return window.confirm("Are you sure you want to edit?")
        }
        function deleteConfirm() {
            return window.confirm("Are you sure you want to delete this?")
        }
    </script>
</body>
</html>
