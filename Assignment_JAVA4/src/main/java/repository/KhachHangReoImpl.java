package repository;
import entity.ChucVu;
import entity.KhachHang;
import entity.NhanVien;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.repositoryImpl.KhachHangRepo;
import repository.repositoryImpl.NhanVienRepo;
import untils.HibernateConfig;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class KhachHangReoImpl implements KhachHangRepo {
    @Override
    public void insertKH(KhachHang kh) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the ChucVu object
            session.save(kh);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<KhachHang> selectAllKH() {
        Transaction transaction = null;
        List<KhachHang> kh = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            kh = session.createQuery("from KhachHang ").getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return kh;
    }

    @Override
    public void updateKH(KhachHang kh) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.saveOrUpdate(kh);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public KhachHang selectKH(UUID khId) {
        Transaction transaction = null;
        KhachHang todo = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            todo = session.get(KhachHang.class, khId);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return todo;
    }

    @Override
    public boolean deleteKH(Integer kh) throws SQLException {
        return false;
    }

    @Override
    public KhachHang getById(UUID khId) {
        KhachHang cv = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            cv = session.get(KhachHang.class, khId);
            if (cv == null) {
                throw new EntityNotFoundException("Không tìm thấy khách hàng với ID: " + khId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cv;
    }

    public static void main(String[] args) {
        KhachHangRepo nhanVienRepo = new KhachHangReoImpl();
        List<KhachHang> list = nhanVienRepo.selectAllKH();
        for (KhachHang khachHang : list) {
            System.out.println(khachHang.toString());
        }
    }
}
