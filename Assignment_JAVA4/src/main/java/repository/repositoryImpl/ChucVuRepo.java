package repository.repositoryImpl;

import entity.ChucVu;
import entity.NhaSanXuat;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface ChucVuRepo {
    void insertCV(ChucVu cv) throws Exception;
    List<ChucVu> selectAllCV();
    void updateCV(ChucVu cv) throws SQLException;
    ChucVu selectCV(long todoId);
    Boolean delete(ChucVu chucVu);
    ChucVu getById(UUID cvId);
}
