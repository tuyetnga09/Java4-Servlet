package repository;

import entity.NhaSanXuat;
import entity.SanPham;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.repositoryImpl.NSXRepo;
import untils.HibernateConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NSXRepoImpl implements NSXRepo {
    @Override
    public void insertNSX(NhaSanXuat nsx) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(nsx);
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
    public List<NhaSanXuat> getAllProducts() {
        Transaction transaction = null;
        List<NhaSanXuat> nhaSanXuats = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            nhaSanXuats = session.createQuery("from NhaSanXuat ").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return nhaSanXuats;
    }

    @Override
    public NhaSanXuat getById(UUID nsxId) {
        NhaSanXuat nhaSanXuat = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            nhaSanXuat = session.get(NhaSanXuat.class, nsxId);
            if (nhaSanXuat == null) {
                throw new EntityNotFoundException("Không tìm thấy chức vụ với ID: " + nsxId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhaSanXuat;
    }

    public NhaSanXuat getProductById(int id) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx = null;
        NhaSanXuat product = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from NhaSanXuat p where p.id = :id");
            query.setParameter("id", id);
            product = (NhaSanXuat) query.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return product;
    }
    public Boolean delete(NhaSanXuat nsx) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(nsx);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public static void main(String[] args) {
        UUID id = UUID.randomUUID();
        NhaSanXuat lopHoc = new NhaSanXuat(id,"NSX34", "NSX34");
        System.out.println(new NSXRepoImpl().delete(lopHoc));
        new NSXRepoImpl().getAllProducts().forEach(s -> System.out.println(s));

    }
}
