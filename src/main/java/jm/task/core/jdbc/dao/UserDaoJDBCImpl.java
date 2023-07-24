package jm.task.core.jdbc.dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private MysqlDataSource mysqlDataSource = Util.getMysqlDataSource();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = mysqlDataSource.getConnection()){
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE Users(id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                                                    "name VARCHAR(255)," +
                                                    "last_name VARCHAR(255)," +
                                                    "age INT);");
        } catch (SQLSyntaxErrorException e){
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void dropUsersTable() {
        try (Connection connection = mysqlDataSource.getConnection()){
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE Users;");
        } catch (SQLSyntaxErrorException e){
            System.out.println(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
            //System.out.println("Exception sql");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = mysqlDataSource.getConnection()){
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Users(name, last_name, age) values (?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
            //System.out.println("Exception sql");
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = mysqlDataSource.getConnection()){
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Users WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
            //System.out.println("Exception sql");
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = mysqlDataSource.getConnection()){
            Statement statement = connection.createStatement();
            boolean hasResults = statement.execute("SELECT * FROM Users;");
            if(hasResults){
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()){
                    User user = new User();
                    user.setId(resultSet.getLong(1));
                    user.setName(resultSet.getString(2));
                    user.setLastName(resultSet.getString(3));
                    user.setAge(resultSet.getByte(4));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
            //System.out.println("Exception sql");
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = mysqlDataSource.getConnection()){
            Statement statement = connection.createStatement();
            statement.execute("TRUNCATE TABLE Users;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
            //System.out.println("Exception sql");
        }
    }
}
