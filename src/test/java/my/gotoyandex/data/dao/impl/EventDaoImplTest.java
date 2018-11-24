package my.gotoyandex.data.dao.impl;

import my.gotoyandex.data.common.hibernateh2.HibernateH2CustomSessionFactoryImpl;
import my.gotoyandex.data.dao.EventDao;
import my.gotoyandex.data.entity.Event;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Timestamp;

public class EventDaoImplTest {

    private EventDao eventDao;

    @Before
    public void init() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        eventDao = new EventDaoImpl(HibernateH2CustomSessionFactoryImpl.getInstance());
    }

    @Test
    public void findByIdAndSaveTest() {
        Event event = new Event();
        event.setType("TEST");
        eventDao.save(event);
        Assert.assertEquals(event, eventDao.findById(1));
        Assert.assertNull(eventDao.findById(2));
    }

    @Test
    public void getEventCountByTimeTest() {
        Timestamp timeFromNotEmpty = new Timestamp(System.currentTimeMillis());
        Event event = new Event();
        event.setType("TEST");
        eventDao.save(event);
        Assert.assertEquals(1, eventDao.getEventCountByTime(timeFromNotEmpty,
                new Timestamp(System.currentTimeMillis()), "TEST"));
        Assert.assertEquals(0, eventDao.getEventCountByTime(timeFromNotEmpty,
                new Timestamp(System.currentTimeMillis()), "TEST_FAIL"));
        Assert.assertEquals(0, eventDao.getEventCountByTime(new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()), "TEST"));
    }
}
