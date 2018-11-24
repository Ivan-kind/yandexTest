package my.gotoyandex.service.impl;

import my.gotoyandex.data.dao.EventDao;
import my.gotoyandex.data.entity.Event;
import my.gotoyandex.service.EventService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;

public class EventServiceImplTest {

    private EventService eventService;
    private EventDao eventDao;

    @Before
    public void init() {
        eventDao = Mockito.mock(EventDao.class);
        Mockito.when(eventDao.getEventCountByTime(Mockito.any(Timestamp.class), Mockito.any(Timestamp.class),
                Mockito.eq(EventServiceImpl.DEF_EVENT_TYPE)))
                .thenReturn(10L);
        eventService = new EventServiceImpl(eventDao);
    }

    @Test
    public void saveEventTest() {
        eventService.saveNewEvent();
        Mockito.verify(eventDao, Mockito.only()).save(Mockito.any(Event.class));
    }

    @Test
    public void getEventCountByLastMinuteTest() {
        long result = eventService.getEventCountByLastMinute();
        Mockito.verify(eventDao, Mockito.only()).getEventCountByTime(Mockito.any(Timestamp.class),
                Mockito.any(Timestamp.class), Mockito.eq(EventServiceImpl.DEF_EVENT_TYPE));
        Assert.assertEquals(result, 10L);
    }

    @Test
    public void getEventCountByLastHourTest() {
        long result = eventService.getEventCountByLastHounrs();
        Mockito.verify(eventDao, Mockito.only()).getEventCountByTime(Mockito.any(Timestamp.class),
                Mockito.any(Timestamp.class), Mockito.eq(EventServiceImpl.DEF_EVENT_TYPE));
        Assert.assertEquals(result, 10L);
    }

    @Test
    public void getEventCountByLastDayTest() {
        long result = eventService.getEventCountByLastDay();
        Mockito.verify(eventDao, Mockito.only()).getEventCountByTime(Mockito.any(Timestamp.class),
                Mockito.any(Timestamp.class), Mockito.eq(EventServiceImpl.DEF_EVENT_TYPE));
        Assert.assertEquals(result, 10L);
    }
}
