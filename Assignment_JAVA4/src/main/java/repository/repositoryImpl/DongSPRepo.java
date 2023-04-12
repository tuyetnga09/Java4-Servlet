package repository.repositoryImpl;
import entity.DongSP;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface DongSPRepo {
    void insertDongSP(DongSP dongSP) throws Exception;
    List<DongSP> selectAllDSP();
    void updateDSP(DongSP cv) throws SQLException;
    DongSP selectDSP(UUID todoId);
    boolean deleteDSP(Integer cv) throws SQLException;
    DongSP getById(UUID cvId);
}
