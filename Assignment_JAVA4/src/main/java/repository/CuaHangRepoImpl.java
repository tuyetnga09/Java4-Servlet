package repository;

import entity.ChucVu;
import entity.CuaHang;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.repositoryImpl.CuaHangRepo;
import untils.HibernateConfig;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class CuaHangRepoImpl implements CuaHangRepo {
    @Override
    public void insertCH(CuaHang ch) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the ChucVu object
            session.save(ch);
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
    public List<CuaHang> selectAllCH() {
        Transaction transaction = null;
        List<CuaHang> cuaHangs = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object

            cuaHangs = session.createQuery("from CuaHang ").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return cuaHangs;
    }

    @Override
    public void updateCH(CuaHang ch) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.saveOrUpdate(ch);
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
    public CuaHang selectCH(UUID todoId) {
        Transaction transaction = null;
        CuaHang todo = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            todo = session.get(CuaHang.class, todoId);
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
    public Boolean delete(CuaHang cuaHang) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(cuaHang);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }


    @Override
    public CuaHang getById(UUID chId) {
        CuaHang ch = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            ch = session.get(CuaHang.class, chId);
            if (ch == null) {
                throw new EntityNotFoundException("Không tìm thấy chức vụ với ID: " + chId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ch;
    }
}
