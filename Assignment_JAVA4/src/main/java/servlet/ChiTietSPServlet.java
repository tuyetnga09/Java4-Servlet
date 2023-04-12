package servlet;

import entity.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import repository.*;
import repository.repositoryImpl.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "ChiTietSPServlet", value = {"/chiTietSP/newCTSP",
        "/chiTietSP/insertCTSP",
        "/chiTietSP/editCTSP",
        "/chiTietSP/updateCTSP",
        "/chiTietSP/deleteCTSP",
        "/chiTietSP/listCTSP",
        "/chiTietSP/sanPhamHeader",
        "/chiTietSP/addToCart",
        "/chiTietSP/gioHang"})
@MultipartConfig(
        location = "/tmp",
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class ChiTietSPServlet extends HttpServlet {
    private SanPhamRepo sanPhamRepo = new SanPhamRepoImpl();
    private NSXRepo nsxRepo = new NSXRepoImpl();
    private MauSacRepo mauSacRepo = new MauSacRepoImpl();
    private DongSPRepo dongSPRepo = new DongSPRepoImpl();
    private ChiTietSPRepo chiTietSPRepo = new ChiTietSPRepoImpl();
    List<Cart> listCart = new ArrayList<Cart>();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("newCTSP")) {
            try {
                this.newCTSP(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (uri.contains("listCTSP")) {
            try {
                this.hienThiCTSP(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (uri.contains("sanPhamHeader")) {
            this.sanPhamHeader(request, response);
        } else if (uri.contains("gioHang")) {
            this.gioHang(request, response);
        } else if (uri.contains("addToCart")) {
            this.addToCart(request, response);
        } else if (uri.contains("deleteCTSP")) {
            this.deleteCTSP(request, response);
        } else {
            this.showEditForm(request, response);
        }

    }

    public void addItem(Cart item) {
        for (Cart i : listCart) {
            if (i.getIdProduct().equals(item.getIdProduct())) {
                i.setSoLuong(i.getSoLuong() + 1);
                return;
            }
        }
        listCart.add(item);
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        ChiTietSP sanPham = chiTietSPRepo.getById(id);
        System.out.println(sanPham);

        // Tạo đối tượng
        Cart item = new Cart();
        item.setIdProduct(sanPham.getId());
        item.setTenSP(sanPham.getListSanPham().getTen());
        item.setSoLuong(1);
        item.setGiaBan(sanPham.getGiaBan());
        item.setHinhAnh(sanPham.getImages());

        // Lấy giỏ hàng từ session
        HttpSession session = request.getSession();
        listCart = (List<Cart>) session.getAttribute("listCart");

        // Nếu giỏ hàng chưa được tạo, tạo mới nó
        if (listCart == null) {
            listCart = new ArrayList<>();
            session.setAttribute("listCart", listCart);
        }

        // Thêm sản phẩm vào giỏ hàng
        addItem(item);

        // Chuyển hướng người dùng đến trang giỏ hàng
        response.sendRedirect("/chiTietSP/gioHang");
    }

    private void gioHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        listCart = (List<Cart>) session.getAttribute("listCart");
        request.setAttribute("listCart", listCart);
        request.getRequestDispatcher("/views/cart.jsp").forward(request, response);
    }


    private void deleteCTSP(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        ChiTietSP ctsp = chiTietSPRepo.getById(id);
        chiTietSPRepo.delete(ctsp);
        List<ChiTietSP> listCTSP = chiTietSPRepo.selectAllCTSP();
        request.setAttribute("listCTSP", listCTSP);
        request.setAttribute("listMS", this.mauSacRepo.selectAllMS());
        request.setAttribute("listSP", this.sanPhamRepo.selectAllSP());
        request.setAttribute("listDSP", this.dongSPRepo.selectAllDSP());
        request.setAttribute("listNSX", this.nsxRepo.getAllProducts());
        request.getRequestDispatcher("/views/chitietSP-hienThi.jsp").forward(request, response);
    }

    private void sanPhamHeader(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        request.setAttribute("listMS", this.mauSacRepo.selectAllMS());
        request.setAttribute("listSP", this.sanPhamRepo.selectAllSP());
        request.setAttribute("listDSP", this.dongSPRepo.selectAllDSP());
        request.setAttribute("listNSX", this.nsxRepo.getAllProducts());
        List<ChiTietSP> listCTSP = chiTietSPRepo.selectAllCTSP();
        request.setAttribute("listCTSP", listCTSP);
        request.getRequestDispatcher("/views/sanPham-header.jsp").forward(request, response);
    }


    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        ChiTietSP editsting = chiTietSPRepo.selectCTSP(id);
        request.setAttribute("listMS", this.mauSacRepo.selectAllMS());
        request.setAttribute("listSP", this.sanPhamRepo.selectAllSP());
        request.setAttribute("listDSP", this.dongSPRepo.selectAllDSP());
        request.setAttribute("listNSX", this.nsxRepo.getAllProducts());
        request.setAttribute("sanPhamSelected", editsting.getListSanPham()); // truyền giá trị đã chọn vào thuộc tính "chucVuSelected"
        request.setAttribute("DSPSelected", editsting.getListDongSP()); // truyền giá trị đã chọn vào thuộc tính "chucVuSelected"
        request.setAttribute("mauSacSelected", editsting.getListMauSac()); // truyền giá trị đã chọn vào thuộc tính "chucVuSelected"
        request.setAttribute("nsxSelected", editsting.getListNhaSanXuat()); // truyền giá trị đã chọn vào thuộc tính "chucVuSelected"

        request.setAttribute("todo", editsting);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/chitietSP-form.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("insertCTSP")) {
            try {
                this.insertCTSP(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                this.updateCTSP(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void updateCTSP(HttpServletRequest request, HttpServletResponse response) throws
            SQLException, IOException {
        ChiTietSP chiTietSP = new ChiTietSP();
        UUID id = UUID.fromString(request.getParameter("id"));
        chiTietSP.setId(id);
        String filename = null;
        try {
            String uploadPath = request.getServletContext().getRealPath("/") + "imageSP";
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
        chiTietSP.setImages(filename);
        chiTietSP.setSoLuongTon(Integer.parseInt(request.getParameter("soLuongTon")));
        chiTietSP.setMoTa(request.getParameter("moTa"));
        chiTietSP.setGiaBan(BigDecimal.valueOf(Long.valueOf(request.getParameter("giaBan"))));
        //mau sac
        String idMSStr = request.getParameter("idMS");
        UUID idMS = null;
        if (idMSStr != null && !idMSStr.isEmpty()) {
            idMS = UUID.fromString(idMSStr);
        }
        // dong sp
        String idDSPStr = request.getParameter("idDSP");
        UUID idDSP = null;
        if (idDSPStr != null && !idDSPStr.isEmpty()) {
            idMS = UUID.fromString(idDSPStr);
        }
        // loai san pham
        String idLSPStr = request.getParameter("idLSP");
        UUID idLSP = null;
        if (idLSPStr != null && !idLSPStr.isEmpty()) {
            idLSP = UUID.fromString(idLSPStr);
        }
        // nha sx
        String idNSXtr = request.getParameter("idNSX");
        UUID idNSX = null;
        if (idNSXtr != null && !idNSXtr.isEmpty()) {
            idNSX = UUID.fromString(idNSXtr);
        }

        MauSac ms = mauSacRepo.getById(idMS);
        chiTietSP.setListMauSac(ms);

        DongSP dongSP = dongSPRepo.getById(idDSP);
        chiTietSP.setListDongSP(dongSP);

        SanPham sp = sanPhamRepo.getById(idLSP);
        chiTietSP.setListSanPham(sp);

        NhaSanXuat nsx = nsxRepo.getById(idNSX);
        chiTietSP.setListNhaSanXuat(nsx);

        chiTietSPRepo.updateCTSP(chiTietSP);
        String imageUrl = request.getContextPath() + "/imageSP/" + filename;
        request.setAttribute("imageUrl", imageUrl);
        response.sendRedirect("/chiTietSP/listCTSP");

    }

    private void insertCTSP(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filename = null;
        try {
            String uploadPath = request.getServletContext().getRealPath("/") + "imageSP";
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
        ChiTietSP chiTietSP = new ChiTietSP();
        chiTietSP.setImages(filename);
        chiTietSP.setSoLuongTon(Integer.parseInt(request.getParameter("soLuongTon")));
        chiTietSP.setMoTa(request.getParameter("moTa"));
        chiTietSP.setGiaBan(BigDecimal.valueOf(Long.valueOf(request.getParameter("giaBan"))));
        //mau sac
        String idMSStr = request.getParameter("idMS");
        UUID idMS = null;
        if (idMSStr != null && !idMSStr.isEmpty()) {
            idMS = UUID.fromString(idMSStr);
        }
        // dong sp
        String idDSPStr = request.getParameter("idDSP");
        UUID idDSP = null;
        if (idDSPStr != null && !idDSPStr.isEmpty()) {
            idDSP = UUID.fromString(idDSPStr);
        }
        // loai san pham
        String idLSPStr = request.getParameter("idSP");
        UUID idLSP = null;
        if (idLSPStr != null && !idLSPStr.isEmpty()) {
            idLSP = UUID.fromString(idLSPStr);
        }
        // nha sx
        String idNSXtr = request.getParameter("idNSX");
        UUID idNSX = null;
        if (idNSXtr != null && !idNSXtr.isEmpty()) {
            idNSX = UUID.fromString(idNSXtr);
        }

        MauSac ms = mauSacRepo.getById(idMS);
        chiTietSP.setListMauSac(ms);

        DongSP dongSP = dongSPRepo.getById(idDSP);
        chiTietSP.setListDongSP(dongSP);

        SanPham sp = sanPhamRepo.getById(idLSP);
        chiTietSP.setListSanPham(sp);

        NhaSanXuat nsx = nsxRepo.getById(idNSX);
        chiTietSP.setListNhaSanXuat(nsx);

        chiTietSPRepo.insertCTSP(chiTietSP);
        String imageUrl = request.getContextPath() + "/imageSP/" + filename;
        request.setAttribute("imageUrl", imageUrl);
        response.sendRedirect("/chiTietSP/listCTSP");

    }

    private void newCTSP(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("listMS", this.mauSacRepo.selectAllMS());
        request.setAttribute("listSP", this.sanPhamRepo.selectAllSP());
        request.setAttribute("listDSP", this.dongSPRepo.selectAllDSP());
        request.setAttribute("listNSX", this.nsxRepo.getAllProducts());
        List<ChiTietSP> listCTSP = chiTietSPRepo.selectAllCTSP();
        request.setAttribute("listCTSP", listCTSP);
        request.getRequestDispatcher("/views/chitietSP-form.jsp").forward(request, response);
    }

    private void hienThiCTSP(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        request.setAttribute("listMS", this.mauSacRepo.selectAllMS());
        request.setAttribute("listSP", this.sanPhamRepo.selectAllSP());
        request.setAttribute("listDSP", this.dongSPRepo.selectAllDSP());
        request.setAttribute("listNSX", this.nsxRepo.getAllProducts());
        List<ChiTietSP> listCTSP = chiTietSPRepo.selectAllCTSP();
        request.setAttribute("listCTSP", listCTSP);
        request.getRequestDispatcher("/views/chitietSP-hienThi.jsp").forward(request, response);
    }

}
