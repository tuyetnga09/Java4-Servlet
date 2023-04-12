package repository.repositoryImpl;

import entity.ChucVu;
import entity.CuaHang;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface CuaHangRepo {
    void insertCH(entity.CuaHang ch) throws Exception;

    List<entity.CuaHang> selectAllCH();

    void updateCH(entity.CuaHang ch) throws SQLException;

    entity.CuaHang selectCH(UUID todoId);

    Boolean delete(CuaHang cuaHang);
    CuaHang getById(UUID chId);
}
