package servlet;

import entity.ChucVu;
import entity.DongSP;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.DongSPRepoImpl;
import repository.repositoryImpl.DongSPRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "DongSPServlet",value = {"/dongSP/newDSP",
        "/dongSP/insertDSP",
        "/dongSP/editDSP",
        "/dongSP/updateDSP",
        "/dongSP/deleteDSP",
        "/dongSP/listDSP"})
public class DongSPServlet extends HttpServlet {
    private DongSPRepo dongSPRepo = new DongSPRepoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("listDSP")) {
            this.hienThiDSP(request, response);
        } else if (uri.contains("newDSP")) {
            this.showNewForm(request, response);
        } else if (uri.contains("editDSP")) {
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if(uri.contains("insertDSP")){
            try {
                this.insertDSP(request, response);
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

    private void hienThiDSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DongSP> listDSP = dongSPRepo.selectAllDSP();
        request.setAttribute("listDSP", listDSP);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/dongSP-hienThi.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/dongSP-form.jsp");
        dispatcher.forward(request, response);
    }
    private void insertDSP(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        DongSP newDSP = new DongSP(ma, ten);
        dongSPRepo.insertDongSP(newDSP);
        response.sendRedirect("/dongSP/listDSP");
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        DongSP editsting = dongSPRepo.selectDSP(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/dongSP-form.jsp");
        request.setAttribute("todo", editsting);
        dispatcher.forward(request, response);
    }
    private void updateCV(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        DongSP updateDSP = new DongSP(id, ma, ten);

        dongSPRepo.updateDSP(updateDSP);

        response.sendRedirect("/dongSP/listDSP");
    }
    private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        dongSPRepo.deleteDSP(id);
        response.sendRedirect("/dongSP/listDSP");
    }


}
