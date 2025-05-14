package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        // создание таблицы
        userService.createUsersTable();

        // добавление пользоватеелй
        User u1 = new User("Ivan", "Belov", (byte) 23);
        User u2 = new User("Mariya", "Ivanova", (byte) 20);
        User u3 = new User("Sergey", "Petrov", (byte) 25);
        User u4 = new User("Irina", "Sidorova", (byte) 28);
        userService.saveUser(u1.getName(), u1.getLastName(), u1.getAge());
        userService.saveUser(u2.getName(), u2.getLastName(), u2.getAge());
        userService.saveUser(u3.getName(), u3.getLastName(), u3.getAge());
        userService.saveUser(u4.getName(), u4.getLastName(), u4.getAge());

        // получение и вывод пользователей
        userService.getAllUsers().forEach(System.out::println);

        // очистка таблицы
        userService.cleanUsersTable();

        // удаление таблицы
        userService.dropUsersTable();











    }
}
