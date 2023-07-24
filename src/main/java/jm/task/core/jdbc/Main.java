package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // implement algorithm here
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.createUsersTable();
        userService.saveUser("name1", "lastName1", (byte) 10);
        userService.saveUser("name2", "lastName2", (byte) 20);
        userService.saveUser("name3", "lastName3", (byte) 30);
        userService.removeUserById(1);
        userService.saveUser("name4", "lastName4", (byte) 40);
        userService.saveUser("name5", "lastName5", (byte) 50);
        userService.saveUser("name6", "lastName6", (byte) 60);
        List<User> list = userService.getAllUsers();
        for (User i: list ) {
            System.out.println(i);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
        userService.dropUsersTable();


    }
}
