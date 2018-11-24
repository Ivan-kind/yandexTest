package my.gotoyandex.data.common;

import org.hibernate.Session;

/**
 * Some wrapper of SessionFactory
 * Somebody can add custom implementation of this interface and use it in EventService
 */
public interface CustomSessionFactory {

    /**
     * Method create new session
     *
     * @return new session
     */
    public Session openSession();
}
