<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Image Gallery</title>
</head>
<body>
<h1>Image Gallery</h1>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>Mã chức vụ</th>
        <th>Tên chức vụ</th>
        <th>Hình ảnh</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <!--   for (Todo todo: todos) {  -->
    <c:forEach var="image" items="${listAll}">

        <tr>
            <td>${image.ma}</td>
            <td>${image.ten}</td>
            <td><img src="/imageNSX/${image.image}"  height = 50px with = 100px>
            <td>
                <a class="btn btn-success" href="edit?id=<c:out value='${image.id}'/>">Update</a>
            <a href="deleteNSX?id=${image.id}" class="btn btn-danger " tabindex="-1" role="button"
               aria-disabled="true">Remove</a>
            </td>
        </tr>
    </c:forEach>
    <!-- } -->
    </tbody>

</table>
</body>
</html>
