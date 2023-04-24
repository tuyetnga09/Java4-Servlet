<%--
  Created by IntelliJ IDEA.
  User: phamtuyetnga
  Date: 15/03/2023
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
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
            <a href="<%=request.getContextPath()%>/nhanVien/listNV" class="navbar-brand">Form thông tin</a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/nhanVien/listNV"
                   class="nav-link">Thông tin</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${todo != null}">
            <form action="updateNV" method="post" enctype="multipart/form-data">
                </c:if>
                <c:if test="${todo == null}">
                <form action="insertNV" method="post" enctype="multipart/form-data">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${todo != null}">
                                Edit Position
                            </c:if>
                            <c:if test="${todo == null}">
                                Add New Position
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${todo != null}">
                        <input type="hidden" type="number" name="id" value="${todo.id}"/>
                    </c:if>
                    <label for="avatar">Choose a profile picture:</label>
                    <input type="file"
                           id="avatar" name="images" value="='${todo.images}'"
                           accept="image/*" enctype="multipart/form-data"
                          >
                    <fieldset class="form-group">
                        <label>Mã nhân viên</label> <input type="text"
                                                           value="<c:out value='${todo.ma}' />" class="form-control"
                                                           name="ma" required="required" minlength="5">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Tên nhân viên</label> <input type="text"
                                                            value="<c:out value='${todo.ten}' />" class="form-control"
                                                            name="ten" required="required" minlength="5">
                    </fieldset>
                        <div class="form-group my-2">
                            <label>Chức vụ</label> <select name="idCV"
                                                             class="form-control">
                            <option disabled="disabled" selected="selected">Chức vụ
                            </option>
                            <c:forEach items="${listCV}" var="cv">
                                <option value="${cv.id}" <c:if test="${cv.id eq todo.listChucVu.id}">selected</c:if>>${cv.ten}</option>
                            </c:forEach>
                        </select>
                        </div>

                    <div class="form-group my-2">
                        <label>Cửa hàng: </label> <select name="idCH"
                                                         class="form-control">
                        <option disabled="disabled" selected="selected">Chọn danh
                            mục cửa hàng
                        </option>
                        <c:forEach items="${listCH}" var="ch">
                            <option value="${ch.id}" <c:if test="${ch.id eq todo.listCuaHang.id}">selected</c:if>>${ch.ten}</option>
                        </c:forEach>
                    </select>
                    </div>
                        <fieldset class="form-group">
                            <label>SDT</label> <input type="text"
                                                                value="<c:out value='${todo.sdt}' />" class="form-control"
                                                                name="sdt" required="required" minlength="5">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Mật khẩu</label> <input type="password"
                                                      value="<c:out value='${todo.matKhau}'/>" class="form-control"
                                                      name="password" required="required" minlength="5">
                        </fieldset>


                        <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
<footer><%@ include file="/layout/footer.jsp" %></footer>
</body>
</html>
