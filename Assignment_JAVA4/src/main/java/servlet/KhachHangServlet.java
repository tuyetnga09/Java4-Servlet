package servlet;

import entity.ChucVu;
import entity.DongSP;
import entity.KhachHang;
import entity.MauSac;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.KhachHangReoImpl;
import repository.repositoryImpl.KhachHangRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "KhachHangServlet", value = {"/khachHang/newKH",
        "/khachHang/insertKH",
        "/khachHang/editKH",
        "/khachHang/updateKH",
        "/khachHang/deleteKH",
        "/khachHang/listKH",
        "/khachHang/thanhToan"})
public class KhachHangServlet extends HttpServlet {
    private KhachHangRepo khachHangRepo = new KhachHangReoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("listKH")) {
            this.hienThiKH(request, response);
        } else if (uri.contains("newKH")) {
            this.showNewForm(request, response);
        } else if (uri.contains("editKH")) {
            this.showEditForm(request, response);
        } else {

        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("insertKH")) {
            try {
                this.insertKH(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if(uri.contains("thanhToan")){
            try {
                this.thanhToan(request,response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else {
            try {
                this.updateKH(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void updateKH(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        UUID id = UUID.fromString(request.getParameter("id"));
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        String sdt = request.getParameter("sdt");
        String diaChi = request.getParameter("diaChi");
        String thanhPho = request.getParameter("thanhPho");

        KhachHang khachHang = new KhachHang();
        khachHang.setId(id);
        khachHang.setMa(ma);
        khachHang.setTen(ten);
        khachHang.setSdt(sdt);
        khachHang.setDiaChi(diaChi);
        khachHang.setThanhPho(thanhPho);
        khachHangRepo.updateKH(khachHang);
        response.sendRedirect("/khachHang/listKH");
    }

    private void insertKH(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        String sdt = request.getParameter("sdt");
        String diaChi = request.getParameter("diaChi");
        String thanhPho = request.getParameter("thanhPho");
        KhachHang khachHang = new KhachHang();
        khachHang.setMa(ma);
        khachHang.setTen(ten);
        khachHang.setSdt(sdt);
        khachHang.setDiaChi(diaChi);
        khachHang.setThanhPho(thanhPho);
        khachHangRepo.insertKH(khachHang);
        response.sendRedirect("/khachHang/listKH");
    }

    private void thanhToan(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        String sdt = request.getParameter("sdt");
        String diaChi = request.getParameter("diaChi");
        String thanhPho = request.getParameter("thanhPho");
        KhachHang khachHang = new KhachHang();
        khachHang.setMa(ma);
        khachHang.setTen(ten);
        khachHang.setSdt(sdt);
        khachHang.setDiaChi(diaChi);
        khachHang.setThanhPho(thanhPho);
        khachHangRepo.insertKH(khachHang);
//        response.sendRedirect("/khachHang/listKH");
        request.getRequestDispatcher("/views/thanhToan.jsp").forward(request,response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/khachhang-form.jsp");
        dispatcher.forward(request, response);
    }

    private void hienThiKH(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<KhachHang> listKH = khachHangRepo.selectAllKH();
        request.setAttribute("listKH", listKH);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/khachhang-hienThi.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        KhachHang editsting = khachHangRepo.selectKH(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/khachhang-form.jsp");
        request.setAttribute("todo", editsting);
        dispatcher.forward(request, response);
    }


}
