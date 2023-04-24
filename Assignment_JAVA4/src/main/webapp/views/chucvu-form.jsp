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
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <div>
            <a href="<%=request.getContextPath()%>/chucVu/insertCV" class="navbar-brand">Form thông tin</a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/chucVu/listCV"
                   class="nav-link">Thông tin</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${todo != null}">
            <form action="updateCV" method="post">
                </c:if>
                <c:if test="${todo == null}">
                <form action="insertCV" method="post">
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
                        <input type="hidden" type = "number" name="id" value="<c:out value='${todo.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Mã chức vụ</label> <input type="text"
                                                         value="<c:out value='${todo.ma}' />" class="form-control"
                                                         name="ma" required="required" minlength="5">
                    </fieldset>

                        <fieldset class="form-group">
                            <label>Tên chức vụ</label> <input type="text"
                                                             value="<c:out value='${todo.ten}' />" class="form-control"
                                                             name="ten" required="required" minlength="5">
                        </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
