<%--
  Created by IntelliJ IDEA.
  User: phamtuyetnga
  Date: 30/03/2023
  Time: 16:24
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
<header>
    <%@ include file="/layout/header.jsp" %>
</header>
<div class="container">
    <h3 class="text-center">LIST PRODUCT</h3>
</div>
<div class="row">
    <c:forEach var="ctsp" items="${listCTSP}">
        <div class="col-lg-4">
            <div class="new">
                <div class="card" style="width: 18rem;">
                    <form action="/chiTietSP/addToCart" method="get" class="btn btn-success">
                        <input  hidden name="id" value="${ctsp.id}">
                        <img src="/imageSP/${ctsp.images}" width="200px"
                             height="250px" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h4 class="card-title">${ctsp.listSanPham.ten}</h4>
                            <h6 class="card-text">Giá bán: ${ctsp.giaBan}</h6>
                            <button type="submit">ADD TO CART</button>
<%--                            <a href="/chiTietSP/addToCart?id=${ctsp.id}">add to cart</a>--%>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </c:forEach>
</div>
<footer>
    <%@ include file="/layout/footer.jsp" %>
</footer>
</body>
</html>
