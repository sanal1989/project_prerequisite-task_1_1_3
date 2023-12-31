package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class Util {

    private Util() {
    }

    private static MysqlDataSource mysqlDataSource;

    private static SessionFactory sessionFactory;

    static {
        mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setUser("bestuser");
        mysqlDataSource.setPassword("bestuser");
        mysqlDataSource.setUrl("jdbc:mysql://localhost:3306/module2");

        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/module2");
        properties.put(Environment.USER, "bestuser");
        properties.put(Environment.PASS, "bestuser");
        sessionFactory = new Configuration()
                .addAnnotatedClass(jm.task.core.jdbc.model.User.class)
                .setProperties(properties)
                .buildSessionFactory();
    }

    public static MysqlDataSource getMysqlDataSource(){
        return mysqlDataSource;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
