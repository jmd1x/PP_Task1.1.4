package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE `users`.`users_table` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastname` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT(3) NULL,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB\n" +
                "DEFAULT CHARACTER SET = utf8;\n";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(sql);
//            System.out.println("Таблица создана");
        } catch (SQLException e) {
//            System.out.println("Таблица уже существует");;
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE USERS_TABLE";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()){
            statement.execute(sql);
//            System.out.println("Таблица удалена");
        } catch (SQLException e) {
//            System.out.println("Таблица не существует");;
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO USERS_TABLE (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User c именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {

        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM USERS_TABLE WHERE id = ?";
        try(Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
//            System.out.println("Удален User с id " + id);
        } catch (SQLException e) {
//            System.out.println("User с id" + id + "не существует");
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM USERS_TABLE";
        try (Connection connection = Util.getConnection();
             ResultSet resultSet = connection.createStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
            for (User u : userList) {
                System.out.println(u);
            }
        } catch (SQLException e) {

        } return userList;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE USERS_TABLE";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()){
            statement.execute(sql);
//            System.out.println("Таблица очищена");
        } catch (SQLException e) {
//            System.out.println("Таблица не существует");;
        }
    }
}
