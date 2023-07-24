package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.DriverManager;

public class Util {
    // set up a database connection
    private static MysqlDataSource mysqlDataSource;

    static {
        mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setUser("bestuser");
        mysqlDataSource.setPassword("bestuser");
        mysqlDataSource.setUrl("jdbc:mysql://localhost:3306/module2");
    }

    public static MysqlDataSource getMysqlDataSource(){
        return mysqlDataSource;
    }
}
