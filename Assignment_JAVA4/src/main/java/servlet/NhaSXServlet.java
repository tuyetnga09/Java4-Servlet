package servlet;

import entity.NhaSanXuat;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import repository.NSXRepoImpl;
import repository.repositoryImpl.NSXRepo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "NhaSXServlet", value = {"/listNSX",
        "/creatensx",
        "/add",
        "/deleteNSX"})
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,//5MB
        fileSizeThreshold = 1024 * 1024 * 1,//1MB
        maxRequestSize = 1024 * 1024 * 10//10MB
)
public class NhaSXServlet extends HttpServlet {
    private NSXRepo nsxRepo = new NSXRepoImpl();
    private NSXRepoImpl nsxRepoImpl = new NSXRepoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("listNSX")) {
            List<NhaSanXuat> listAll = nsxRepo.getAllProducts();
//            System.out.println(listAll.get(0).getImage());
            request.setAttribute("listAll", listAll);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/hienThi.jsp");
            dispatcher.forward(request, response);
        } else if (uri.contains("add")) {
            this.showNewForm(request, response);
        } else if (uri.contains("deleteNSX")) {
            this.deleteNSX(request, response);
        }
    }

    private void deleteNSX(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        NhaSanXuat nsx = nsxRepo.getById(id);
        nsxRepo.delete(nsx);
        List<NhaSanXuat> listAll = nsxRepo.getAllProducts();
        request.setAttribute("listAll", listAll);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/hienThi.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("creatensx")) {
            try {
                this.add(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/nsx.jsp");
        dispatcher.forward(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String filename = null;
        try {
            String uploadPath = request.getServletContext().getRealPath("/") + "imageNSX";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            Part part = request.getPart("hinhAnh");
            filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

            part.write(uploadPath + "/" + filename);

        } catch (Exception e) {
            e.printStackTrace();
        }
        NhaSanXuat nsx = new NhaSanXuat();
        nsx.setMa(request.getParameter("ma"));
        nsx.setTen(request.getParameter("ten"));
        nsx.setImage(filename);
        nsxRepo.insertNSX(nsx);
        String imageUrl = request.getContextPath() + "/imageNSX/" + filename;
        request.setAttribute("imageUrl", imageUrl);
        response.sendRedirect("listNSX");

    }
}

