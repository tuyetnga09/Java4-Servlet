package repository;

import entity.DongSP;
import entity.MauSac;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.repositoryImpl.MauSacRepo;
import untils.HibernateConfig;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class MauSacRepoImpl implements MauSacRepo {
    @Override
    public void insertMS(MauSac mauSac) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the ChucVu object
            session.save(mauSac);
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
    public List<MauSac> selectAllMS() {
        Transaction transaction = null;
        List<MauSac> todos = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object

            todos = session.createQuery("from MauSac ").getResultList();

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
    public void updateMS(MauSac mauSac) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.saveOrUpdate(mauSac);
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
    public MauSac selectMS(UUID todoId) {
        Transaction transaction = null;
        MauSac todo = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            todo = session.get(MauSac.class, todoId);
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
    public boolean deleteMS(Integer ms) throws SQLException {
        return false;
    }

    @Override
    public MauSac getById(UUID msId) {
        MauSac mauSac = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            mauSac = session.get(MauSac.class, msId);
            if (mauSac == null) {
                throw new EntityNotFoundException("Không tìm thấy màu sắc với ID: " + msId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mauSac;
    }
}
