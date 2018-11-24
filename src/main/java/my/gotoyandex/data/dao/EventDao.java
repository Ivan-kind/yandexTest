package my.gotoyandex.data.dao;

import my.gotoyandex.data.entity.Event;

import java.sql.Timestamp;

public interface EventDao {

    /**
     * Method that find event by id
     *
     * @param id event id
     * @return event
     */
    public Event findById(long id);

    /**
     * Method that save new event
     *
     * @param event object to save
     */
    public void save(Event event);

    /**
     * Method to find statistic with count of registried events
     *
     * @param timeFrom search time from
     * @param timeTo search time to
     * @param type type of event
     * @return count of registried events
     */
    public long getEventCountByTime(Timestamp timeFrom, Timestamp timeTo, String type);
}
