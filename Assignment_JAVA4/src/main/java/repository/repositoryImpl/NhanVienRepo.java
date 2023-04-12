package repository.repositoryImpl;

import entity.NhanVien;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface NhanVienRepo {
    Boolean insertNV(NhanVien nv) throws Exception;
    List<NhanVien> selectAllNV();
    void updateNV(NhanVien nv) throws SQLException;
    NhanVien selectNV(UUID nvId);
    boolean deleteNV(NhanVien nv) throws SQLException;
    NhanVien getById(UUID nvId);

//    ArrayList<NhanVien> getByMaNV(String maNV);
}
