<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="./css/plugin/style.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

</head>
<body>

<section>
    <!--Bắt Đầu Phần Hình Ảnh-->
    <div class="img-bg">
        <img src="https://niemvuilaptrinh.ams3.cdn.digitaloceanspaces.com/tao_trang_dang_nhap/hinh_anh_minh_hoa.jpg"
             alt="Hình Ảnh Minh Họa">
    </div>


    <div class="noi-dung">
        <div class="form">
            <h2>Trang Đăng Nhập</h2>
            <% String error = (String) request.getAttribute("error"); %>
            <% if (error != null) { %>
            <p style="color: red;"><%= error %>
            </p>
            <% } %>
            <% String mess = (String) request.getAttribute("mess"); %>
            <% if (mess != null) { %>
            <p style="color: red;"><%= mess %>
            </p>
            <% } %>
            <form action="/login" method="post">
                <div class="input-form">
                    <span>Tên Người Dùng</span>
                    <input type="text" name="username">
                </div>
                <div class="input-form">
                    <span>Mật Khẩu</span>
                    <input type="password" name="password">
                </div>
                <div class="nho-dang-nhap">
                    <label><input type="checkbox" name=""> Nhớ Đăng Nhập</label>
                </div>
                <div class="input-form">
                    <button type="submit" class="btn btn-danger">Login</button>
                </div>
                <div class="input-form">
                    <p>Bạn Chưa Có Tài Khoản? <a href="/nhanVien/newNV">Đăng Ký</a></p>
                </div>
            </form>
            <h3>Đăng Nhập Bằng Mạng Xã Hội</h3>
            <ul class="icon-dang-nhap">
                <li><i class="fa fa-facebook" aria-hidden="true"></i></li>
                <li><i class="fa fa-google" aria-hidden="true"></i></li>
                <li><i class="fa fa-twitter" aria-hidden="true"></i></li>
            </ul>
        </div>
    </div>
    <!--Kết Thúc Phần Nội Dung-->
</section>
</body>
</html>