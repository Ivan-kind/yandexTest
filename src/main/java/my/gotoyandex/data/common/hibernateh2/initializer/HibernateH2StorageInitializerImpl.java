package my.gotoyandex.data.common.hibernateh2.initializer;

import my.gotoyandex.data.entity.Event;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HibernateH2StorageInitializerImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateH2StorageInitializerImpl.class);

    public void initStorage() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        LOGGER.trace("initStorage - start");
        try {
            Class.forName("org.h2.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");
            LOGGER.debug("initStorage - conn : {}", conn);
            Statement st = conn.createStatement();
            st.execute("CREATE TABLE EVENT (\n" +
                    "  ID BIGINT PRIMARY KEY auto_increment,\n" +
                    "  TYPE varchar(30) NOT NULL,\n" +
                    "  CREATED_AT TIMESTAMP NOT NULL default CURRENT_TIMESTAMP \n" +
                    ")");
        } catch (Exception e) {
            LOGGER.error("Error on init storage!", e);
            throw e;
        }
    }

    public Configuration getConfiguration() {
        LOGGER.trace("getConfiguration - start");
        return new Configuration()
                .setProperty( "hibernate.connection.driver_class",
                        "org.h2.Driver" )
                .setProperty( "hibernate.connection.url", "jdbc:h2:mem:test" )
                .setProperty( "hibernate.connection.pool_size", "10" )
                .setProperty( "hibernate.connection.autocommit", "false" )
                .setProperty( "hibernate.cache.provider_class",
                        "org.hibernate.cache.NoCacheProvider" )
                .setProperty( "hibernate.cache.use_second_level_cache",
                        "false" )
                .setProperty( "hibernate.cache.use_query_cache", "false" )
                .setProperty( "hibernate.dialect",
                        "org.hibernate.dialect.H2Dialect" )
                .setProperty( "hibernate.show_sql","true" )
                .setProperty( "hibernate.current_session_context_class",
                        "thread" )
                .addAnnotatedClass(Event.class);
    }
}
