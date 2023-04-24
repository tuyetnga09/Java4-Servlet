<%--
  Created by IntelliJ IDEA.
  User: phamtuyetnga
  Date: 04/04/2023
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
  <link rel="stylesheet" href="../css/plugin/menu.css">
  <link rel="stylesheet" href="../css/plugin/header.css">
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
        integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

</head>
<body>
<header><%@ include file="/layout/header.jsp" %></header>
<div class="menu">
  <ul>
    <li><a href="/nhanVien/listNV">Quản lý nhân viên</a></li>
    <li><a href="/chiTietSP/listCTSP">Quản lý sản phẩm</a></li>
    <li><a href="/khachHang/listKH">Quản lý khách hàng</a></li>
    <li><a href="#">Thống kê</a></li>
    <li><a href="#">Báo cáo</a></li>
  </ul>
</div>
<div class="gioiThieu">
  <h1>WELLCOME TO MY PET</h1>
  <p>Để xem các thông tin vui lòng chọn vào thanh menu!</p>
  <p>Xin chân trọng cảm ơn</p>
</div>

</body>

</html>
