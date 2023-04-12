package servlet;

import entity.ChiTietSP;
import entity.ChucVu;
import entity.CuaHang;
import entity.NhanVien;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.ChucVuRepoImpl;
import repository.CuaHangRepoImpl;
import repository.NhanVienRepoImpl;
import repository.repositoryImpl.ChucVuRepo;
import repository.repositoryImpl.CuaHangRepo;
import repository.repositoryImpl.NhanVienRepo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "NhanVienServlet", value = {"/nhanVien/newNV",
        "/nhanVien/insertNV",
        "/nhanVien/editNV",
        "/nhanVien/updateNV",
        "/nhanVien/deleteNV",
        "/nhanVien/listNV",
        "/quanLy"
})
@MultipartConfig(
        location = "/tmp",
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class NhanVienServlet extends HttpServlet {
    private ChucVuRepo chucVuRepo = new ChucVuRepoImpl();
    private CuaHangRepo cuaHangRepo = new CuaHangRepoImpl();
    private NhanVienRepo nhanVienRepo = new NhanVienRepoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("newNV")) {
            try {
                this.newNV(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (uri.contains("listNV")) {
            try {
                this.hienThiNV(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (uri.contains("deleteNV")) {
            try {
                this.deleteNV(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } else if (uri.contains("quanLy")) {
            request.getRequestDispatcher("/views/menu.jsp").forward(request, response);

        } else{
            try {
                this.showEditForm(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void deleteNV(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        NhanVien nv = nhanVienRepo.getById(id);
        nhanVienRepo.deleteNV(nv);
        request.setAttribute("listCV", this.chucVuRepo.selectAllCV());
        request.setAttribute("listCH", this.cuaHangRepo.selectAllCH());
        List<NhanVien> listNV = nhanVienRepo.selectAllNV();
        request.setAttribute("listNV", listNV);
        request.getRequestDispatcher("/views/nhanVien-hienThi.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("insertNV")) {
            try {
                this.insertNV(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else{
            try {
                this.updateNV(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void newNV(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("listCV", this.chucVuRepo.selectAllCV());
        request.setAttribute("listCH", this.cuaHangRepo.selectAllCH());
        List<NhanVien> listNV = nhanVienRepo.selectAllNV();
        request.setAttribute("listNV", listNV);
        request.getRequestDispatcher("/views/staff-form.jsp").forward(request, response);
    }

    private void hienThiNV(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("listCV", this.chucVuRepo.selectAllCV());
        request.setAttribute("listCH", this.cuaHangRepo.selectAllCH());
        List<NhanVien> listNV = nhanVienRepo.selectAllNV();
        request.setAttribute("listNV", listNV);
        request.getRequestDispatcher("/views/nhanVien-hienThi.jsp").forward(request, response);
    }

    private void insertNV(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filename = null;
        try {
            String uploadPath = request.getServletContext().getRealPath("/") + "imageNV";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
          Part part = request.getPart("images");
            filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

            // Log to check if the file upload is working properly
            System.out.println("Upload path: " + uploadPath);
            System.out.println("File name: " + filename);

            part.write(uploadPath + "/" + filename);

        } catch (Exception e) {
            e.printStackTrace();
        }
        NhanVien nv = new NhanVien();
        nv.setImages(filename);
        nv.setMa(request.getParameter("ma"));
        nv.setTen(request.getParameter("ten"));
        nv.setSdt(request.getParameter("sdt"));
        nv.setMatKhau(request.getParameter("matKhau"));
        String idCVStr = request.getParameter("idCV");
        UUID idCV = null;
        if (idCVStr != null && !idCVStr.isEmpty()) {
            idCV = UUID.fromString(idCVStr);
        }
        String idCHStr = request.getParameter("idCH");
        UUID idCH = null;
        if (idCHStr != null && !idCHStr.isEmpty()) {
            idCH = UUID.fromString(idCHStr);
        }
        ChucVu cv =  chucVuRepo.getById(idCV);
        nv.setListChucVu(cv);
        CuaHang cuaHang = cuaHangRepo.getById(idCH);
        nv.setListCuaHang(cuaHang);

        nhanVienRepo.insertNV(nv);
        String imageUrl = request.getContextPath() + "/imageNV/" + filename;
        request.setAttribute("imageUrl", imageUrl);
        response.sendRedirect("/nhanVien/listNV");

    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        NhanVien editsting = nhanVienRepo.selectNV(id);
        request.setAttribute("listCV", this.chucVuRepo.selectAllCV());
        request.setAttribute("listCH", this.cuaHangRepo.selectAllCH());
        request.setAttribute("todo", editsting);
        request.setAttribute("chucVuSelected", editsting.getListChucVu()); // truyền giá trị đã chọn vào thuộc tính "chucVuSelected"
        request.setAttribute("cuHangSelected", editsting.getListCuaHang()); // truyền giá trị đã chọn vào thuộc tính "chucVuSelected"
        String imageUrl = request.getContextPath() + "/imageNV/" + editsting.getImages();
        request.setAttribute("imageUrl", imageUrl);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/staff-form.jsp");
        dispatcher.forward(request, response);
    }

    private void updateNV(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String filename = null;
        try {
            String uploadPath = request.getServletContext().getRealPath("/") + "imageNV";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            Part part = request.getPart("images");
            filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

            // Log to check if the file upload is working properly
            System.out.println("Upload path: " + uploadPath);
            System.out.println("File name: " + filename);

            part.write(uploadPath + "/" + filename);

        } catch (Exception e) {
            e.printStackTrace();
        }
        NhanVien nv = new NhanVien();
        UUID id = UUID.fromString(request.getParameter("id"));
        nv.setId(id);
        nv.setImages(filename);
        nv.setMa(request.getParameter("ma"));
        nv.setTen(request.getParameter("ten"));
        nv.setSdt(request.getParameter("sdt"));
        nv.setMatKhau(request.getParameter("matKhau"));
        String idCVStr = request.getParameter("idCV");
        UUID idCV = null;
        if (idCVStr != null && !idCVStr.isEmpty()) {
            idCV = UUID.fromString(idCVStr);
        }
        String idCHStr = request.getParameter("idCH");
        UUID idCH = null;
        if (idCHStr != null && !idCHStr.isEmpty()) {
            idCH = UUID.fromString(idCHStr);
        }
        ChucVu cv =  chucVuRepo.getById(idCV);
        nv.setListChucVu(cv);
        CuaHang cuaHang = cuaHangRepo.getById(idCH);
        nv.setListCuaHang(cuaHang);
        nhanVienRepo.updateNV(nv);
        String imageUrl = request.getContextPath() + "/imageNV/" + filename;
        request.setAttribute("imageUrl", imageUrl);
        response.sendRedirect("/nhanVien/listNV");
    }
}


