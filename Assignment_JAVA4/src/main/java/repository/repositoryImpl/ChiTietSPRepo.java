package repository.repositoryImpl;


import entity.ChiTietSP;
import entity.ChucVu;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface ChiTietSPRepo {
    Boolean insertCTSP(ChiTietSP ctsp) throws Exception;

    List<ChiTietSP> selectAllCTSP();

    void updateCTSP(ChiTietSP chiTietSP) throws SQLException;

    ChiTietSP selectCTSP(UUID idCTSP);

    Boolean delete(ChiTietSP chiTietSP);

    ChiTietSP getById(UUID ctsp);
    


}
