package repository.repositoryImpl;

import entity.MauSac;
import entity.SanPham;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface SanPhamRepo {
    void insertSP(SanPham sanPham) throws Exception;

    List<SanPham> selectAllSP();

    void updateSP(SanPham sanPham) throws SQLException;

    SanPham selectSP(UUID todoId);

    boolean deleteSP(Integer ms) throws SQLException;

    SanPham getById(UUID spId);
}
