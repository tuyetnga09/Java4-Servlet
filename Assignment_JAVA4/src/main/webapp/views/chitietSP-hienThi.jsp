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

<div class="">
    <header>
        <nav class="navbar navbar-expand-md navbar-dark"
             style="background-color: tomato">
            <div>
                <a href="<%=request.getContextPath()%>/chiTietSP/newCTSP" class="navbar-brand">ADD PRODUCT</a>
            </div>

            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/chiTietSP/listCTSP"
                       class="nav-link">INFOMATION PRODUCT</a></li>
            </ul>
        </nav>
    </header>
</div>
<div class="">
    <div class="container">
        <h3 class="text-center">LIST PRODUCT</h3>
        <hr>
        <div class="container text-left">
            <form method="post" action="search">
                <div class="form-group">
                    <input type="text" name="keyword" class="form-control" placeholder="Enter product name...">
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>

<%--            <a href="<%=request.getContextPath()%>/chiTietSP/newCTSP"--%>
<%--               class="btn btn-success">ADD PRODUCT</a>--%>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Ảnh sản phẩm</th>
                <th>Màu sắc</th>
                <th>Dòng sản phẩm</th>
                <th>Loại sản phẩm</th>
                <th>Nhà sản xuất</th>
                <th>Số lượng tồn</th>
                <th>Giá bán</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ctsp" items="${listCTSP}">

                <tr>
                    <td><img src="/imageSP/${ctsp.images}" height=90px with=140px></td>
                    <td>${ctsp.listMauSac.ten}</td>
                    <td>${ctsp.listDongSP.ten}</td>
                    <td>${ctsp.listSanPham.ten}</td>
                    <td>${ctsp.listNhaSanXuat.ten}</td>
                    <td>${ctsp.soLuongTon}</td>
                    <td>${ctsp.giaBan}</td>
                    <td>
                        <a class="btn btn-success" href="/chiTietSP/editCTSP?id=<c:out value='${ctsp.id}'/>">Update</a>
                        <a class="btn btn-warning" href="/chiTietSP/deleteCTSP?id=<c:out value='${ctsp.id}' />">Delete</a>
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
