package repository;

import entity.MauSac;
import entity.SanPham;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.repositoryImpl.SanPhamRepo;
import untils.HibernateConfig;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class SanPhamRepoImpl implements SanPhamRepo {
    @Override
    public void insertSP(SanPham sanPham) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the ChucVu object
            session.save(sanPham);
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
    public List<SanPham> selectAllSP() {
        Transaction transaction = null;
        List<SanPham> todos = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object

            todos = session.createQuery("from SanPham ").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return todos;
    }

    @Override
    public void updateSP(SanPham sanPham) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.saveOrUpdate(sanPham);
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
    public SanPham selectSP(UUID todoId) {
        Transaction transaction = null;
        SanPham sanPham = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            sanPham = session.get(SanPham.class, todoId);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return sanPham;
    }

    @Override
    public boolean deleteSP(Integer ms) throws SQLException {
        return false;
    }

    @Override
    public SanPham getById(UUID spId) {
        SanPham sanPham = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            sanPham = session.get(SanPham.class, spId);
            if (sanPham == null) {
                throw new EntityNotFoundException("Không tìm thấy chức vụ với ID: " + spId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sanPham;
    }
}
