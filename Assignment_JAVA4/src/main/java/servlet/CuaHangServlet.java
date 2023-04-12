package servlet;
import entity.ChucVu;
import entity.CuaHang;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.CuaHangRepoImpl;
import repository.repositoryImpl.CuaHangRepo;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "CuaHangServlet", value = {"/cuaHang/newCH",
        "/cuaHang/insertCH",
        "/cuaHang/editCH",
        "/cuaHang/updateCH",
        "/cuaHang/deleteCH",
        "/cuaHang/listCH"})
public class CuaHangServlet extends HttpServlet {
    private CuaHangRepo cuaHangRepo = new CuaHangRepoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("listCH")) {
            this.hienThiCV(request, response);
        } else if (uri.contains("newCH")) {
            this.showNewForm(request, response);
        } else if (uri.contains("editCH")) {
            this.showEditForm(request, response);
        } else {
            this.deleteTodo(request, response);
        }
    }

    private void deleteTodo(HttpServletRequest request, HttpServletResponse response) {
        UUID id = UUID.fromString(request.getParameter("id"));
        CuaHang ch = cuaHangRepo.getById(id);
        cuaHangRepo.delete(ch);
        List<CuaHang> cuaHang = cuaHangRepo.selectAllCH();
        request.setAttribute("listCH", cuaHang);
        request.getRequestDispatcher("/views/cua-hang-hienThi.jsp");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        CuaHang editsting = cuaHangRepo.selectCH(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/cua-hang-form.jsp");
        request.setAttribute("todo", editsting);
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/cua-hang-form.jsp");
        dispatcher.forward(request, response);
    }

    private void hienThiCV(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CuaHang> listCH = cuaHangRepo.selectAllCH();
        request.setAttribute("listCH", listCH);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/cua-hang-hienThi.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("insertCH")) {
            try {
                this.insertCH(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                this.updateCH(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void updateCH(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        String diaChi = request.getParameter("diaChi");
        String thanhPho = request.getParameter("thanhPho");
        String quocGia = request.getParameter("quocGia");
        CuaHang updateCH = new CuaHang();
        updateCH.setId(id);
        updateCH.setMa(ma);
        updateCH.setTen(ten);
        updateCH.setDiaChi(diaChi);
        updateCH.setThanhPho(thanhPho);
        updateCH.setQuocGia(quocGia);
        cuaHangRepo.updateCH(updateCH);

        response.sendRedirect("/cuaHang/listCH");
    }

    private void insertCH(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        String diaChi = request.getParameter("diaChi");
        String thanhPho = request.getParameter("thanhPho");
        String quocGia = request.getParameter("quocGia");
        CuaHang newCH = new CuaHang(ma, ten, diaChi, thanhPho, quocGia);
        cuaHangRepo.insertCH(newCH);
        response.sendRedirect("/cuaHang/listCH");

    }
}
