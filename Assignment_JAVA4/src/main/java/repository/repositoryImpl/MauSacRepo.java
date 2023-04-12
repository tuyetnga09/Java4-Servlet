package repository.repositoryImpl;


import entity.MauSac;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface MauSacRepo {
    void insertMS(MauSac mauSac) throws Exception;

    List<MauSac> selectAllMS();

    void updateMS(MauSac mauSac) throws SQLException;

    MauSac selectMS(UUID todoId);

    boolean deleteMS(Integer ms) throws SQLException;

    MauSac getById(UUID msId);
}
