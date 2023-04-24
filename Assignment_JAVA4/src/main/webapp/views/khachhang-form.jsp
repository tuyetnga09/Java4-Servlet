
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <div>
            <a href="<%=request.getContextPath()%>/khachHang/newKH" class="navbar-brand">Form thông tin</a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/khachHang/listKH"
                   class="nav-link">Thông tin</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${todo != null}">
            <form action="/khachHang/updateKH" method="post">
                </c:if>
                <c:if test="${todo == null}">
                <form action="/khachHang/insertKH" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${todo != null}">
                                Edit Custom
                            </c:if>
                            <c:if test="${todo == null}">
                                Add New Custom
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

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
<footer><%@ include file="/layout/footer.jsp" %></footer>
</body>
</html>
