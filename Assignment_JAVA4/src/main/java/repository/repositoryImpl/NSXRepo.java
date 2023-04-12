package repository.repositoryImpl;

import entity.MauSac;
import entity.NhaSanXuat;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface NSXRepo {
    void insertNSX(NhaSanXuat nsx) throws Exception;

    List<NhaSanXuat> getAllProducts();

    NhaSanXuat getById(UUID nsxId);
    Boolean delete(NhaSanXuat nsx);

}
