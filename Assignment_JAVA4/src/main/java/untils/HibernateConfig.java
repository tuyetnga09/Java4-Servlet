package untils;

import entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateConfig {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
                settings.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=JAVA4");
                settings.put(Environment.USER, "sa");
                settings.put(Environment.PASS, "123456aA@$");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.SQLServer2008Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(ChucVu.class);
                configuration.addAnnotatedClass(CuaHang.class);
                configuration.addAnnotatedClass(DongSP.class);
                configuration.addAnnotatedClass(NhaSanXuat.class);
                configuration.addAnnotatedClass(NhanVien.class);
                configuration.addAnnotatedClass(MauSac.class);
                configuration.addAnnotatedClass(SanPham.class);
                configuration.addAnnotatedClass(DongSP.class);
                configuration.addAnnotatedClass(ChiTietSP.class);
                configuration.addAnnotatedClass(KhachHang.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void main(String[] args) {
        getSessionFactory();
    }
}
