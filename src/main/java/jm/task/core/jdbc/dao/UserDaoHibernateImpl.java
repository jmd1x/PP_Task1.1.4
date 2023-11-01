package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    //private static final SessionFactory sessionFactory = Util.getSessionFactory();

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE `users`.`users_table` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastname` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT(3) NULL,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB\n" +
                "DEFAULT CHARACTER SET = utf8;\n";
        try (Session session = Util.getSessionFactory().openSession()) {
//            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
//        } finally {
//            sessionFactory.close();
        } catch (Exception e) {

            System.out.println("Исключение в createUsersTable");
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE users_table";
        try (Session session = Util.getSessionFactory().openSession()) {
//            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
//        } finally {
//            sessionFactory.close();
        } catch (Exception e) {
            System.out.println("Исключение в dropUsersTable");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try (Session session = Util.getSessionFactory().openSession()) {
//            Session session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
//        } finally {
//            sessionFactory.close();
//        }
        } catch (Exception e) {
            System.out.println("Исключение в dropUsersTable");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
//            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
//        } finally {
//            sessionFactory.close();
//        }
        } catch (Exception e) {
            System.out.println("Исключение в removeUserById");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()) {
//            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            userList = session.createQuery("from User", User.class).getResultList();
            session.getTransaction().commit();

//        } finally {
//            sessionFactory.close();
//        }
        } catch (Exception e) {
            System.out.println("Исключение в getAllUsers");
        }
        for (User u : userList) {
            System.out.println(u);
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
//            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE users_table").executeUpdate();
            session.getTransaction().commit();
//        } finally {
//            sessionFactory.close();
//        }
        } catch (Exception e) {
            System.out.println("Исключение в cleanUsersTable");
        }
    }
}
