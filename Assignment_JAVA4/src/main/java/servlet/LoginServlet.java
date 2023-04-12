package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy tên người dùng và mật khẩu từ request parameter
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username.isEmpty() || password.isEmpty()) {
            request.setAttribute("mess", "Tài khoản hoặc mât khẩu đang trống");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        // Kiểm tra tên người dùng và mật khẩu
        if (username.equals("ngaptt09") && password.equals("ngaptt09")) {
            // Tạo HttpSession và lưu tên người dùng vào session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            System.out.println(session.getAttribute("username"));

            // Chuyển hướng đến trang chủ
            response.sendRedirect("views/menu.jsp");
        } else if (username.equals("xuanvu30") && password.equals("xuanvu30")) {
            // Tạo HttpSession và lưu tên người dùng vào session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            System.out.println(session.getAttribute("username"));

            // Chuyển hướng đến trang chủ
            response.sendRedirect("views/trangChu.jsp");
        } else if (username.isEmpty() || password.isEmpty()) {
            request.setAttribute("mess", "Tài khoản hoặc mât khẩu đang trống");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            // Nếu tên người dùng hoặc mật khẩu không đúng, hiển thị thông báo lỗi
            request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
