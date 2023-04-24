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
                <a href="../views/sanPham-form.jsp" class="navbar-brand">Form Sản phẩm </a>
            </div>

            <ul class="navbar-nav">
                <li><a action="/sanPham/listSP" me
                       class="nav-link">Thông tin</a></li>
            </ul>
        </nav>
    </header>
</div>
<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">Danh sách sản phẩm</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/sanPham/newSP"
               class="btn btn-success">ADD PRODUCT</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Mã sản phẩm</th>
                <th>Tên sản phẩm</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="sp" items="${listSP}">

                <tr>
                    <td>${sp.ma}</td>
                    <td>${sp.ten}</td>
                    <td>
                        <a class="btn btn-success" href="/sanPham/editSP?id=<c:out value='${sp.id}'/>">Update</a>
                        <a class="btn btn-warning" href="/sanPham/deleteSP?id=<c:out value='${sp.id}' />">Delete</a>
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
