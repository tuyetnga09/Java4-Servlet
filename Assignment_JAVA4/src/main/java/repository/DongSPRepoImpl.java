package repository;

import entity.ChucVu;
import entity.DongSP;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.repositoryImpl.DongSPRepo;
import untils.HibernateConfig;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class DongSPRepoImpl implements DongSPRepo {
    @Override
    public void insertDongSP(DongSP dongSP) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the ChucVu object
            session.save(dongSP);
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
    public List<DongSP> selectAllDSP() {
        Transaction transaction = null;
        List<DongSP> todos = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object

            todos = session.createQuery("from DongSP ").getResultList();

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
    public void updateDSP(DongSP cv) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.saveOrUpdate(cv);
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
    public DongSP selectDSP(UUID todoId) {
        Transaction transaction = null;
        DongSP todo = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            todo = session.get(DongSP.class, todoId);
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
    public boolean deleteDSP(Integer cv) throws SQLException {
        return false;
    }

    @Override
    public DongSP getById(UUID cvId) {
        DongSP dongSP = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            dongSP = session.get(DongSP.class, cvId);
            if (dongSP == null) {
                throw new EntityNotFoundException("Không tìm thấy chức vụ với ID: " + cvId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dongSP;
    }

}
