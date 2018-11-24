package my.gotoyandex.service;

/**
 * The main interface of this lib.
 * Use this interface to save some events and find som statistics
 */
public interface EventService {

    /**
     * Save new event with default type
     */
    public void saveNewEvent();

    /**
     * Method to find statistic about count of registried events at last minute
     *
     * @return count of events
     */
    public long getEventCountByLastMinute();

    /**
     * Method to find statistic about count of registried events at last hour
     *
     * @return count of events
     */
    public long getEventCountByLastHounrs();

    /**
     * Method to find statistic about count of registried events at last day
     *
     * @return count of events
     */
    public long getEventCountByLastDay();

}
