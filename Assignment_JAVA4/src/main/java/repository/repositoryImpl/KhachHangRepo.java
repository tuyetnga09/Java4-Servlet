package repository.repositoryImpl;

import entity.DongSP;
import entity.KhachHang;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface KhachHangRepo {
    void insertKH(KhachHang kh) throws Exception;

    List<KhachHang> selectAllKH();

    void updateKH(KhachHang kh) throws SQLException;

    KhachHang selectKH(UUID khId);

    boolean deleteKH(Integer kh) throws SQLException;

    KhachHang getById(UUID khId);
}
