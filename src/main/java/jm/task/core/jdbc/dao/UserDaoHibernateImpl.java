package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE Users(id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(255)," +
                    "last_name VARCHAR(255)," +
                    "age INT);").executeUpdate();
            transaction.commit();
        }catch (PersistenceException e){
            System.out.println(e);
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE Users;").executeUpdate();
            transaction.commit();
        }catch (PersistenceException e){
            System.out.println(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            NativeQuery<User> query = session.createSQLQuery("INSERT INTO Users(name, last_name, age) values (:name,:lastName,:age)");
            query.setParameter("name", name);
            query.setParameter("lastName", lastName);
            query.setParameter("age", age);
            query.executeUpdate();
            transaction.commit();
        }catch (PersistenceException e){
            System.out.println(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            NativeQuery<User> query = session.createSQLQuery("DELETE FROM Users WHERE id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
        }catch (PersistenceException e){
            System.out.println(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            NativeQuery<User> query = session.createNativeQuery("SELECT * FROM Users;", User.class);
            list = query.list();
            transaction.commit();
        }catch (PersistenceException e){
            System.out.println(e);
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE Users;").executeUpdate();
            transaction.commit();
        }
    }
}
