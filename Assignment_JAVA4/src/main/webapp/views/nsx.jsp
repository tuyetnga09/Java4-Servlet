<%--
  Created by IntelliJ IDEA.
  User: phamtuyetnga
  Date: 23/03/2023
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<form action="creatensx" method="post" enctype="multipart/form-data" >
    <div class="row mt-4">
        <div class="col-6">
            <label>Ma</label>
            <input type="text" class="form-control" name="ma"/>
        </div>
    </div>
    <div class="row mt-4">
        <div class="col-6">
            <label>Ten</label>
            <input type="text" class="form-control" name="ten"/>
        </div>
    </div>
    <label for="avatar">Choose a profile picture:</label>

    <input type="file"
           id="avatar" name="hinhAnh"
           accept="image/*" enctype="multipart/form-data">
    <button class="btn btn-success col-1 m-3" type="submit">
        ADD
    </button>
</form>
</body>
</html>
