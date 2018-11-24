package my.gotoyandex.data.dao.impl;

import my.gotoyandex.data.dao.EventDao;
import my.gotoyandex.data.entity.Event;
import my.gotoyandex.data.common.CustomSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class EventDaoImpl implements EventDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventDaoImpl.class);

    private final CustomSessionFactory customSessionFactory;

    public EventDaoImpl(CustomSessionFactory customSessionFactory) {
        this.customSessionFactory = customSessionFactory;
    }

    @Override
    public Event findById(long id) {
        LOGGER.trace("findById - start id : {}", id);
        Session session = customSessionFactory.openSession();
        Event result = (Event) session.get(Event.class, id);
        session.close();
        LOGGER.trace("findById - end result : {}", result);
        return result;
    }

    @Override
    public void save(Event event) {
        if (Objects.isNull(event)) {
            LOGGER.warn("Error on save event! Object to save must not be null");
            throw new IllegalArgumentException("Event to save is null!");
        }
        LOGGER.trace("save - start event : {}", event);
        Session session = customSessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        Serializable saveResult = session.save(event);
        LOGGER.debug("save event : {} result : {}", event, saveResult);
        tx1.commit();
        session.close();
        LOGGER.trace("save - end");
    }

    @Override
    public long getEventCountByTime(Timestamp timeFrom, Timestamp timeTo, String type) {
        LOGGER.trace("getEventCountByTime - start timeFrom : {} timeTo", timeFrom, timeTo);
        Session session = customSessionFactory.openSession();
        Query query = session.createQuery("select count(id) from Event where created_at>=:timeFrom " +
                "and created_at<=:timeTo and type=:someType");
        query.setTimestamp("timeFrom", timeFrom);
        query.setTimestamp("timeTo", timeTo);
        query.setString("someType", type);
        long count = (long) query.uniqueResult();
        LOGGER.debug("getEventCountByTime - have {} events between ( {} , {} )", count, timeFrom, timeTo);
        session.close();
        return count;
    }
}
