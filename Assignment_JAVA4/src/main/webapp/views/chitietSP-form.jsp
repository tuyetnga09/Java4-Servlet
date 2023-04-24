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
            <a href="<%=request.getContextPath()%>/chiTietSP/newCTSP" class="navbar-brand">ADD PRODUCT</a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/chiTietSP/listCTSP"
                   class="nav-link">INFOMATION PRODUCT</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-9">
    <div class="card">
        <div class="card-body">
            <c:if test="${todo != null}">
            <form action="/chiTietSP/updateCTSP" method="post" enctype="multipart/form-data">
                </c:if>
                <c:if test="${todo == null}">
                <form action="/chiTietSP/insertCTSP" method="post" enctype="multipart/form-data">
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
                           id="avatar" name="images"
                           accept="image/*" enctype="multipart/form-data">

                    <div class="form-group my-2">
                        <label>Nhà sản xuất</label> <select name="idNSX"
                                                            class="form-control">
                        <option disabled="disabled" selected="selected">Nhà sản xuất
                        </option>
                        <c:forEach items="${listNSX}" var="nsx">
                            <option value="${nsx.id}"  <c:if test="${nsx.id eq todo.listNhaSanXuat.id}">selected</c:if>>${nsx.ten}</option>
                        </c:forEach>
                    </select>
                        <div class="form-group my-2">
                            <label>Màu sắc</label> <select name="idMS"
                                                           class="form-control">
                            <option disabled="disabled" selected="selected">Màu sắc
                            </option>
                            <c:forEach items="${listMS}" var="ms">
                                <option value="${ms.id}"  <c:if test="${ms.id eq todo.listMauSac.id}">selected</c:if>>${ms.ten}</option>
                            </c:forEach>
                        </select>
                        </div>

                        <div class="form-group my-2">
                            <label>Dòng sản phẩm:</label> <select name="idDSP"
                                                                  class="form-control">
                            <option disabled="disabled" selected="selected">Dòng sản phẩm
                            </option>
                            <c:forEach items="${listDSP}" var="dsp">
                                <option value="${dsp.id}"  <c:if test="${dsp.id eq todo.listDongSP.id}">selected</c:if>>${dsp.ten}</option>
                            </c:forEach>
                        </select>
                        </div>

                        <div class="form-group my-2">
                            <label>Loại sản phẩm:</label> <select name="idSP"
                                                                  class="form-control">
                            <option disabled="disabled" selected="selected">Loại sản phẩm
                            </option>
                            <c:forEach items="${listSP}" var="lsp">
                                <option value="${lsp.id}"  <c:if test="${lsp.id eq todo.listSanPham.id}">selected</c:if>>${lsp.ten}</option>
                            </c:forEach>
                        </select>
                        </div>
                        <fieldset class="form-group">
                            <label>Số lượng tồn</label> <input type="number"
                                                               value="<c:out value='${todo.soLuongTon}' />"
                                                               class="form-control"
                                                               name="soLuongTon" required="required" minlength="5">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Mô tả</label> <input type="text"
                                                        value="<c:out value='${todo.soLuongTon}' />"
                                                        class="form-control"
                                                        name="soLuongTon" required="required" minlength="5">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Giá bán</label> <input type="number"
                                                          value="<c:out value='${todo.giaBan}'/>" class="form-control"
                                                          name="giaBan" required="required">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                    </div>
                </form>
                    <span><a class="btn btn-warning" href="/sanPham/newSP">ADD CATEGORY</a></span>
                    <span><a class="btn btn-success" href="/add">ADD NHÀ SẢN XUẤT</a></span>
                    <span><a class="btn btn-info" href="/mauSac/newMS">ADD MÀU SẮC</a></span>
                    <span><a  class="btn btn-danger" href="/dongSP/newDSP">ADD DÒNG SẢN PHẨM</a></span>
        </div>
    </div>
</div>
<footer><%@ include file="/layout/footer.jsp" %></footer>
</body>
</html>
