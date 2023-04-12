package servlet;

import entity.ChucVu;
import entity.NhaSanXuat;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.ChucVuRepoImpl;
import repository.repositoryImpl.ChucVuRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "ChucVuServlet", value = {"/chucVu/newCV",
        "/chucVu/insertCV",
        "/chucVu/editCV",
        "/chucVu/updateCV",
        "/chucVu/deleteCV",
        "/chucVu/listCV"})
public class ChucVuServlet extends HttpServlet {
    private ChucVuRepo chucVuRepo = new ChucVuRepoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("listCV")) {
            this.hienThiCV(request, response);
        } else if (uri.contains("newCV")) {
            this.showNewForm(request, response);
        } else if (uri.contains("editCV")) {
            try {
                this.showEditForm(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            try {
                this.deleteTodo(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void hienThiCV(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ChucVu> listCV = chucVuRepo.selectAllCV();
        request.setAttribute("listCV", listCV);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/chucVu-hienThi.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if(uri.contains("insertCV")){
            try {
                this.insertCV(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else{
            try {
                this.updateCV(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/chucvu-form.jsp");
        dispatcher.forward(request, response);
    }
    private void insertCV(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");

        ChucVu newCV = new ChucVu(ma, ten);
        chucVuRepo.insertCV(newCV);
        response.sendRedirect("/chucVu/listCV");
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        ChucVu editsting = chucVuRepo.getById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/chucvu-form.jsp");
        request.setAttribute("todo", editsting);
        dispatcher.forward(request, response);
    }
    private void updateCV(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        ChucVu updateCV = new ChucVu(id, ma, ten);

        chucVuRepo.updateCV(updateCV);

        response.sendRedirect("/chucVu/listCV");
    }
    private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        UUID id = UUID.fromString(request.getParameter("id"));
        ChucVu cv = chucVuRepo.getById(id);
        chucVuRepo.delete(cv);
        List<ChucVu> chucVu = chucVuRepo.selectAllCV();
        request.setAttribute("listCV", chucVu);
        request.getRequestDispatcher("/views/chucVu-hienThi.jsp");
    }


}
