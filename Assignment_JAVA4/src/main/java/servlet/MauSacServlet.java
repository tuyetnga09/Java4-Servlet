package servlet;

import entity.DongSP;
import entity.MauSac;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.MauSacRepoImpl;
import repository.repositoryImpl.MauSacRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "MauSacServlet", value = {"/mauSac/newMS",
        "/mauSac/insertMS",
        "/mauSac/editMS",
        "/mauSac/updateMS",
        "/mauSac/deleteMS",
        "/mauSac/listMS"})
public class MauSacServlet extends HttpServlet {
    private MauSacRepo mauSacRepo = new MauSacRepoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("listMS")) {
            this.hienThiMS(request, response);
        } else if (uri.contains("newMS")) {
            this.showNewForm(request, response);
        } else if (uri.contains("editMS")) {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("insertMS")) {
            try {
                this.insertMS(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                this.updateMS(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void hienThiMS(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MauSac> listMS = mauSacRepo.selectAllMS();
        request.setAttribute("listMS", listMS);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/mauSac-hienThi.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/mauSac-form.jsp");
        dispatcher.forward(request, response);
    }
    private void insertMS(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        MauSac newMS = new MauSac(ma, ten);
        mauSacRepo.insertMS(newMS);
        response.sendRedirect("/mauSac/listMS");
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        MauSac editsting = mauSacRepo.selectMS(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/mauSac-form.jsp");
        request.setAttribute("todo", editsting);
        dispatcher.forward(request, response);
    }
    private void updateMS(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        MauSac updateMS = new MauSac(id, ma, ten);

        mauSacRepo.updateMS(updateMS);

        response.sendRedirect("/mauSac/listMS");
    }
    private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        mauSacRepo.deleteMS(id);
        response.sendRedirect("/mauSac/listMS");
    }
}
