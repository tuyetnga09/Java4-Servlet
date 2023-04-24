<%--
  Created by IntelliJ IDEA.
  User: phamtuyetnga
  Date: 21/03/2023
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<header><%@ include file="/layout/header.jsp" %></header>

    <div class="banner">
        <img
                src="../asset/hinh-anh-banner-dien-may-thong-minh_033705387.png"
                alt=""
        />
    </div>
    <div class="sp-moi">
        <font face="Comic sans MS"
        ><u><h2>Sản Phẩm Mới</h2></u></font
        >
    </div>
    <div class="row">
        <div class="col-lg-4">
            <div class="new">
                <div class="card" style="width: 18rem">
                    <img src="../asset/img1.webp" class="card-img-top" alt="..." />
                    <div class="card-body">
                        <b><h5 class="card-title">IPhone 14 Pro Max</h5></b>
                        <span class="card-text">28.390.000₫</span>
                        <span><del>35.000.000₫</del></span>
                        <a href="#" class="btn btn-primary">Mua ngay</a>
                        <span><img src="../asset/img10.gif" alt="" /></span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4">
            <div class="new">
                <div class="card" style="width: 18rem">
                    <img
                            src="../asset/img2.webp"
                            width="120px"
                            ,
                            height="190px"
                            class="card-img-top"
                            alt="..."
                    />
                    <div class="card-body">
                        <b><h5 class="card-title">iPhone 14 Pro 128GB</h5></b>
                        <span class="card-text">25.490.000₫ </span>
                        <span><del>30.990.000₫</del></span>
                        <a href="#" class="btn btn-primary">Mua ngay</a>
                        <span><img src="../asset/img10.gif" alt="" /></span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4">
            <div class="new">
                <div class="card" style="width: 18rem">
                    <img
                            src="../asset/img3.png"
                            width="120px"
                            height="195px"
                            class="card-img-top"
                            alt="..."
                    />
                    <div class="card-body">
                        <b><h5 class="card-title">iPhone 14 128GB</h5></b>
                        <span class="card-text">19.900.000₫ </span>
                        <span><del>24.990.000₫</del></span>
                        <a href="#" class="btn btn-primary">Mua ngay</a>
                        <span><img src="../asset/img10.gif" alt="" /></span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4">
            <div class="new">
                <div class="card" style="width: 18rem">
                    <img
                            src="../asset/img4.webp"
                            width="120px"
                            height="195px"
                            class="card-img-top"
                            alt="..."
                    />
                    <div class="card-body">
                        <b><h5 class="card-title">MacBook Pro 13 M2 2022</h5></b>
                        <span class="card-text">30.390.000₫</span>
                        <span><del>35.990.000₫</del></span>
                        <a href="#" class="btn btn-primary">Mua ngay</a>
                        <span><img src="../asset/img10.gif" alt="" /></span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4">
            <div class="new">
                <div class="card" style="width: 18rem">
                    <img
                            src="../asset/img5.webp"
                            width="120px"
                            height="195px"
                            class="card-img-top"
                            alt="..."
                    />
                    <div class="card-body">
                        <b><h5 class="card-title">MacBook Pro M1 2020</h5></b>
                        <span class="card-text">28.550.000₫ </span>
                        <span><del>34.990.000₫</del></span>
                        <a href="#" class="btn btn-primary">Mua ngay</a>
                        <span><img src="../asset/img10.gif" alt="" /></span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4">
            <div class="new">
                <div class="card" style="width: 18rem">
                    <img
                            src="../asset/img6.png"
                            width="120px"
                            height="195px"
                            class="card-img-top"
                            alt="..."
                    />
                    <div class="card-body">
                        <b
                        ><h5 class="card-title">
                            MacBook Air M2 2022 (8GB RAM | 256GB SSD)
                        </h5></b
                        >
                        <span class="card-text">26.890.000₫</span>
                        <span><del>32.990.000₫</del></span>
                        <a href="#" class="btn btn-primary">Mua ngay</a>
                        <span><img src="../asset/img10.gif" alt="" /></span>

                    </div>
                </div>
            </div>
        </div>
    </div>

<footer><%@ include file="/layout/footer.jsp" %></footer>

</body>
</html>
