<%--
  Created by IntelliJ IDEA.
  User: phamtuyetnga
  Date: 21/03/2023
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/plugin/header.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
</head>
<body>
<div class="row">
    <div class="col-lg-2">
        <img src="../asset/logo.png" alt="" />
    </div>
    <div class="col-lg-8">
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <font face="Comic sans MS"
                ><b><a  href="./trangChu.jsp">Trang chủ </a></b></font
                >
                <button
                        class="navbar-toggler"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#navbarNav"
                        aria-controls="navbarNav"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                >
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <font face="Comic sans MS"
                            ><b
                            ><a
                                    class="nav-link active"
                                    aria-current="page"
                                    href="#gioithieu"
                            >Giới thiệu</a
                            ></b
                            ></font
                            >
                        </li>

                        <li class="nav-item">
                            <font face="Comic sans MS"
                            ><b
                            ><a
                                    class="nav-link active"
                                    aria-current="page"
                                    href="/chiTietSP/sanPhamHeader"
                            >Sản phẩm</a
                            ></b
                            ></font
                            >
                        </li>
                        <li class="nav-item">
                            <font face="Comic sans MS"
                            ><b
                            ><a class="nav-link active" aria-current="page" href=""
                            >Sự kiện</a
                            ></b
                            ></font
                            >
                        </li>

                        <li class="nav-item">
                            <font face="Comic sans MS"
                            ><b
                            ><a
                                    class="nav-link active"
                                    aria-current="page"
                                    href="/quanLy"
                            >Quản Lý</a
                            ></b
                            ></font
                            >
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
    <div class="col-lg-2">
        <div class="cart">
            <button type="button" class="btn btn-primary position-relative">
                <a href="#gioHang"
                ><img
                        src="../asset/4105931-add-to-cart-buy-cart-sell-shop-shopping-cart_113919.png"
                        alt=""
                /></a>

                <span
                        class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger"
                >
          1
          <span class="visually-hidden">unread messages</span>
        </span>
            </button>

            <button type="button" class="btn btn-primary position-relative">
                <a href="#muaHang"> <img src="../asset/tim.png" alt="" /></a>

                <span
                        class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger"
                >
          1
          <span class="visually-hidden">unread messages</span>
        </span>
            </button>
        </div>
    </div>
</div>


</body>
</html>
