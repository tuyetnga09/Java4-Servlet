package servlet;

import entity.SanPham;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.SanPhamRepoImpl;
import repository.repositoryImpl.SanPhamRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "SanPhamServlet", value = {"/sanPham/newSP",
        "/sanPham/insertSP",
        "/sanPham/editSP",
        "/sanPham/updateSP",
        "/sanPham/deleteSP",
        "/sanPham/listSP"})
public class SanPhamServlet extends HttpServlet {
     SanPhamRepo sanPhamRepo = new SanPhamRepoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("listSP")) {
            this.hienThiSP(request, response);
        } else if (uri.contains("newSP")) {
            this.showNewForm(request, response);
        } else if (uri.contains("editSP")) {
            try {
                this.showEditForm(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                this.deleteTodo(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String uri = request.getRequestURI();
        if (uri.contains("insertSP")) {
            try {
                this.insertSP(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                this.updateSP(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void hienThiSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SanPham> listSP = sanPhamRepo.selectAllSP();
        request.setAttribute("listSP", listSP);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/sanPham-hienThi.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/sanPham-form.jsp");
        dispatcher.forward(request, response);
    }
    private void insertSP(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        SanPham newSP = new SanPham(ma, ten);
        sanPhamRepo.insertSP(newSP);
        response.sendRedirect("/sanPham/listSP");
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        SanPham editsting = sanPhamRepo.selectSP(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/sanPham-form.jsp");
        request.setAttribute("todo", editsting);
        dispatcher.forward(request, response);
    }
    private void updateSP(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        SanPham updateSP = new SanPham(id, ma, ten);
        sanPhamRepo.updateSP(updateSP);

        response.sendRedirect("/sanPham/listSP");
    }
    private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        sanPhamRepo.deleteSP(id);
        response.sendRedirect("/sanPham/listSP");
    }
}
