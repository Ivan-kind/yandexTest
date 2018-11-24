package my.gotoyandex.service.impl;

import my.gotoyandex.data.dao.EventDao;
import my.gotoyandex.data.dao.impl.EventDaoImpl;
import my.gotoyandex.data.entity.Event;
import my.gotoyandex.data.common.hibernateh2.HibernateH2CustomSessionFactoryImpl;
import my.gotoyandex.data.common.CustomSessionFactory;
import my.gotoyandex.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Timestamp;

public class EventServiceImpl implements EventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

    public static final String DEF_EVENT_TYPE = "I_VORGUL_TEST_DEF_TYPE";
    private static final long ONE_MINUTE = 60 * 1000;
    private static final long ONE_HOUR = 60 * 60 * 1000;
    private static final long ONE_DAY = 24 * 60 * 60 * 1000;

    private EventDao eventDao;

    public EventServiceImpl() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        this(new EventDaoImpl(HibernateH2CustomSessionFactoryImpl.getInstance()));
    }

    public EventServiceImpl(CustomSessionFactory customSessionFactory) {
        this(new EventDaoImpl(customSessionFactory));
    }

    public EventServiceImpl(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    public void saveNewEvent() {
        LOGGER.trace("saveNewEvent - start with default type : {}", DEF_EVENT_TYPE);
        Event event = new Event();
        event.setType(DEF_EVENT_TYPE);
        eventDao.save(event);
        LOGGER.trace("saveNewEvent - end");
    }

    @Override
    public long getEventCountByLastMinute() {
        LOGGER.trace("getEventCountByLastMinute - start");
        long currTimeLong = System.currentTimeMillis();
        Timestamp timeTo = new Timestamp(currTimeLong);
        Timestamp timeFrom = new Timestamp(currTimeLong - ONE_MINUTE);
        LOGGER.debug("getEventCountByLastMinute - timeFrom : {} timeTo : {}", timeFrom, timeTo);
        return eventDao.getEventCountByTime(timeFrom, timeTo, DEF_EVENT_TYPE);
    }

    @Override
    public long getEventCountByLastHounrs() {
        LOGGER.trace("getEventCountByLastHounrs - start");
        long currTimeLong = System.currentTimeMillis();
        Timestamp timeTo = new Timestamp(currTimeLong);
        Timestamp timeFrom = new Timestamp(currTimeLong - ONE_HOUR);
        LOGGER.debug("getEventCountByLastHounrs - timeFrom : {} timeTo : {}", timeFrom, timeTo);
        return eventDao.getEventCountByTime(timeFrom, timeTo, DEF_EVENT_TYPE);
    }

    @Override
    public long getEventCountByLastDay() {
        LOGGER.trace("getEventCountByLastDay - start");
        long currTimeLong = System.currentTimeMillis();
        Timestamp timeTo = new Timestamp(currTimeLong);
        Timestamp timeFrom = new Timestamp(currTimeLong - ONE_DAY);
        LOGGER.debug("getEventCountByLastDay - timeFrom : {} timeTo : {}", timeFrom, timeTo);
        return eventDao.getEventCountByTime(timeFrom, timeTo, DEF_EVENT_TYPE);
    }
}
