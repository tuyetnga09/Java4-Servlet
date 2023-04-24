<%--
  Created by IntelliJ IDEA.
  User: phamtuyetnga
  Date: 15/03/2023
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<div class="row">
    <header>
        <nav class="navbar navbar-expand-md navbar-dark"
             style="background-color: tomato">
            <div>
                <a href="<%=request.getContextPath()%>/cuaHang/newCH" class="navbar-brand">Form thông tin</a>
            </div>

            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/cuaHang/listCH"
                       class="nav-link">Thông tin</a></li>
            </ul>
        </nav>
    </header>
</div>
<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">Danh sách cửa hàng</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/cuaHang/newCH"
               class="btn btn-success">ADD POSITION</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Mã cửa hàng</th>
                <th>Tên cửa hàng</th>
                <th>Địa chỉ</th>
                <th>Thành phố</th>
                <th>Quốc gia</th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="ch" items="${listCH}">

                <tr>
                    <td>${ch.ma}</td>
                    <td>${ch.ten}</td>
                    <td>${ch.diaChi}</td>
                    <td>${ch.thanhPho}</td>
                    <td>${ch.quocGia}</td>

                    <td>
                        <a class="btn btn-success" href="editCH?id=<c:out value='${ch.id}'/>">Update</a>
                        <a href="/cuaHang/deleteCH?id=${ch.id}" class="btn btn-danger " tabindex="-1" role="button"
                           aria-disabled="true">Remove</a>
                    </td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>

</body>
</html>
