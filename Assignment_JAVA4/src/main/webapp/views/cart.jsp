<%--
  Created by IntelliJ IDEA.
  User: phamtuyetnga
  Date: 03/04/2023
  Time: 23:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<%--    <link href="../css/plugin/gioHang.css">--%>
</head>
<body>
<h4>GIỎ HÀNG</h4>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Hình ảnh</th>
        <th scope="col">Tên sản phẩm</th>
        <th scope="col">Số lượng</th>
        <th scope="col">Giá bán</th>
        <th scope="col">Thành tiền</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listCart}" var="v">
        <tr>
            <td><img src="/imageSP/${v.hinhAnh}" height=90px with=140px></td>
            <td>${v.tenSP}</td>
            <td>${v.soLuong}</td>
            <td>${v.giaBan}</td>
            <td>${v.soLuong * v.giaBan}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${todo != null}">
            <form action="/khachHang/updateKH" method="post">
                </c:if>
                <c:if test="${todo == null}">
                <form action="/khachHang/thanhToan" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${todo != null}">
                                Edit Custom
                            </c:if>
                            <c:if test="${todo == null}">
                                Thông tin khách hàng
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${todo != null}">
                        <input type="hidden" type = "number" name="id" value="<c:out value='${todo.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Mã khách hàng</label> <input type="text"
                                                            value="<c:out value='${todo.ma}' />" class="form-control"
                                                            name="ma" required="required" minlength="5">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Tên khách hàng</label> <input type="text"
                                                             value="<c:out value='${todo.ten}' />" class="form-control"
                                                             name="ten" required="required" minlength="5">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Số điện thoại</label> <input type="text"
                                                            value="<c:out value='${todo.sdt}' />" class="form-control"
                                                            name="sdt" required="required" minlength="5">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Địa chỉ</label> <input type="text"
                                                      value="<c:out value='${todo.diaChi}' />" class="form-control"
                                                      name="diaChi" required="required" minlength="5">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Thành phố</label> <input type="text"
                                                        value="<c:out value='${todo.thanhPho}' />" class="form-control"
                                                        name="thanhPho" required="required" minlength="5">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Thanh toán</button>

                </form>
        </div>
    </div>
</div>
<a href="/chiTietSP/sanPhamHeader" class="btn btn-success">BACK</a>
</body>
</html>
