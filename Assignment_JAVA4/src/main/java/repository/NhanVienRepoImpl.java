package repository;

import entity.NhanVien;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.repositoryImpl.NhanVienRepo;
import untils.HibernateConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NhanVienRepoImpl implements NhanVienRepo {
    @Override
    public Boolean insertNV(NhanVien nv) throws Exception {
        Transaction transaction;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(nv);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<NhanVien> selectAllNV() {
        Transaction transaction = null;
        List<NhanVien> nvien = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object

            nvien = session.createQuery("from NhanVien ").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return nvien;
    }

    @Override
    public void updateNV(NhanVien nv) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.saveOrUpdate(nv);
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
    public NhanVien selectNV(UUID nvId) {
        Transaction transaction = null;
        NhanVien todo = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            todo = session.get(NhanVien.class, nvId);
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
    public boolean deleteNV(NhanVien nv) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(nv);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    @Override
    public NhanVien getById(UUID nvId) {
        NhanVien nv = new NhanVien();
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            nv = session.get(NhanVien.class, nvId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return nv;
    }

    private List<NhanVien> searchEmployeesByName(String name) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<NhanVien> criteria = builder.createQuery(NhanVien.class);
        Root<NhanVien> root = criteria.from(NhanVien.class);
        criteria.select(root).where(builder.like(root.get("Ten"), "%" + name + "%"));
        List<NhanVien> employees = session.createQuery(criteria).getResultList();
        session.close();
        return employees;
    }
    public static void main(String[] args) {
        NhanVienRepo nhanVienRepo = new NhanVienRepoImpl();
        List<NhanVien> list = nhanVienRepo.selectAllNV();
        for (NhanVien khachHang : list) {
            System.out.println(khachHang.toString());
        }
    }
}
