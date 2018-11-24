package my.gotoyandex.data.common.hibernateh2;

import my.gotoyandex.data.common.CustomSessionFactory;
import my.gotoyandex.data.common.hibernateh2.initializer.HibernateH2StorageInitializerImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.sql.SQLException;
import java.util.Objects;

public class HibernateH2CustomSessionFactoryImpl implements CustomSessionFactory {

    private static volatile HibernateH2CustomSessionFactoryImpl instance;

    private final SessionFactory sessionFactory;

    private HibernateH2CustomSessionFactoryImpl() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        HibernateH2StorageInitializerImpl storageInitializer = new HibernateH2StorageInitializerImpl();
        storageInitializer.initStorage();
        Configuration configuration = storageInitializer.getConfiguration();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static HibernateH2CustomSessionFactoryImpl getInstance() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        if (Objects.isNull(instance)) {
            synchronized (HibernateH2CustomSessionFactoryImpl.class) {
                if (Objects.isNull(instance)) {
                    instance = new HibernateH2CustomSessionFactoryImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Session openSession() {
        return sessionFactory.openSession();
    }
}
