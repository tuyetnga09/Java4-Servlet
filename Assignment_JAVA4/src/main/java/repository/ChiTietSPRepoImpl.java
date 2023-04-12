package repository;

import entity.ChiTietSP;
import entity.ChucVu;
import entity.NhanVien;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.repositoryImpl.ChiTietSPRepo;
import untils.HibernateConfig;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ChiTietSPRepoImpl implements ChiTietSPRepo {
    @Override
    public Boolean insertCTSP(ChiTietSP ctsp) throws Exception {
        Transaction transaction;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(ctsp);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ChiTietSP> selectAllCTSP() {
        Transaction transaction = null;
        List<ChiTietSP> chiTietSPS = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object

            chiTietSPS = session.createQuery("from ChiTietSP ").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return chiTietSPS;
    }

    @Override
    public void updateCTSP(ChiTietSP chiTietSP) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.saveOrUpdate(chiTietSP);
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
    public ChiTietSP selectCTSP(UUID idCTSP) {
        Transaction transaction = null;
        ChiTietSP todo = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            todo = session.get(ChiTietSP.class, idCTSP);
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
    public Boolean delete(ChiTietSP chiTietSP) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(chiTietSP);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }


    @Override
    public ChiTietSP getById(UUID ctsp) {
        ChiTietSP chiTietSP = new ChiTietSP();
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            chiTietSP = session.get(ChiTietSP.class, ctsp);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietSP;
    }


}
