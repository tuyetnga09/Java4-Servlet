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
                <a href="<%=request.getContextPath()%>/nhanVien/newNV" class="navbar-brand">Form thông tin</a>
            </div>

            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/nhanVien/listNV"
                       class="nav-link">Thông tin</a></li>
            </ul>
        </nav>
    </header>
</div>
<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">Danh sách nhân viên</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/nhanVien/newNV"
               class="btn btn-success">ADD STAFF</a>
        </div>
        <br>
        <form method="post" action="search">
            <div class="form-group">
                <input type="text" name="keyword" class="form-control" placeholder="Enter product name...">
            </div>
            <button type="submit" class="btn btn-primary">Search</button>
        </form>

        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Ảnh thẻ</th>
                <th>Mã nhân viên</th>
                <th>Họ tên</th>
                <th>Số điện thoại</th>
                <th>Chức vụ</th>
                <th>QUản lý cửa hàng</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="nv" items="${listNV}">

                <tr>
                    <td><img src="/imageNV/${nv.images}"  height=90px with=140px>
                    <td>${nv.ma}</td>
                    <td>${nv.ten}</td>
                    <td>${nv.sdt}</td>
                    <td>${nv.listChucVu.ten}</td>
                    <td>${nv.listCuaHang.ten}</td>
                    <td>
                        <a class="btn btn-success" href="/nhanVien/editNV?id=<c:out value='${nv.id}'/>">Update</a>
                        <a class="btn btn-warning" href="/nhanVien/deleteNV?id=<c:out value='${nv.id}' />">Delete</a>
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
