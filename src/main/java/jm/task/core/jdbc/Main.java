package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Jose", "Mourinho", (byte) 55);
        userService.saveUser("Frank", "Lampard", (byte) 45);
        userService.saveUser("Wayne", "Rooney", (byte) 43);
        userService.saveUser("Alexander", "Kerzhakov", (byte) 39);

        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
        // реализуйте алгоритм здесь
    }
}
