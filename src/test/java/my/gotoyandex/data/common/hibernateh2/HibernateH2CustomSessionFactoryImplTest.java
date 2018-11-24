package my.gotoyandex.data.common.hibernateh2;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class HibernateH2CustomSessionFactoryImplTest {

    @Test
    public void getInstanceTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Assert.assertSame(HibernateH2CustomSessionFactoryImpl.getInstance(), HibernateH2CustomSessionFactoryImpl.getInstance());
    }

    @Test
    public void openSessionTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        HibernateH2CustomSessionFactoryImpl factory = HibernateH2CustomSessionFactoryImpl.getInstance();
        Assert.assertNotEquals(factory.openSession(), factory.openSession());
    }
}
