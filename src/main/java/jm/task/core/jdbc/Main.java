package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Jose", "Mourinho", (byte) 55);
        userDao.saveUser("Frank", "Lampard", (byte) 45);
        userDao.saveUser("Wayne", "Rooney", (byte) 43);
        userDao.saveUser("Alexander", "Kerzhakov", (byte) 39);

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
        // реализуйте алгоритм здесь
    }
}
