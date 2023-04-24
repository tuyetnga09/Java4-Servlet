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
<header><%@ include file="/layout/header.jsp" %></header>
<div class="row">
    <header>
        <nav class="navbar navbar-expand-md navbar-dark"
             style="background-color: tomato">
            <div>
                <a href="<%=request.getContextPath()%>/khachHang/newKH" class="navbar-brand">FORM KHÁCH HÀNG</a>
            </div>

            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/khachHang/listKH"
                       class="nav-link">Thông tin</a></li>
            </ul>
        </nav>
    </header>
</div>
<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">DANH SÁCH KHÁCH HÀNG</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/khachHang/newKH"
               class="btn btn-success">ADD KHÁCH HÀNG</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Mã khách hàng</th>
                <th>Tên khách hàng</th>
                <th>Số điện thoại</th>
                <th>Địa chỉ</th>
                <th>Thành phố</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="kh" items="${listKH}">

                <tr>
                    <td>${kh.ma}</td>
                    <td>${kh.ten}</td>
                    <td>${kh.sdt}</td>
                    <td>${kh.diaChi}</td>
                    <td>${kh.thanhPho}</td>
                    <td>
                        <a class="btn btn-success" href="/khachHang/editKH?id=<c:out value='${kh.id}'/>">Update</a>
                        <a class="btn btn-warning" href="/khachHang/deleteKH?id=<c:out value='${kh.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
</div>
<footer><%@ include file="/layout/footer.jsp" %></footer>
</body>
</html>
