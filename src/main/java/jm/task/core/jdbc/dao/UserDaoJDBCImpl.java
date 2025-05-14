package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(20) NOT NULL, " +
                "lastname VARCHAR(20) NOT NULL, " +
                "age TINYINT UNSIGNED NOT NULL" +
                ")";

        try (Connection conn = Util.getConnection()) {
            if (Objects.nonNull(conn)) {
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (Connection conn = Util.getConnection()) {
            if (Objects.nonNull(conn)) {
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)";
        try (Connection conn = Util.getConnection()) {
            if (Objects.nonNull(conn)) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, name);
                stmt.setString(2, lastName);
                stmt.setByte(3, age);
                stmt.executeUpdate();
                System.out.println("User с именем - " + name + " добавлен в базу данных");
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }

    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = Util.getConnection()) {
            if (Objects.nonNull(conn)) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setLong(1, id);
                stmt.executeUpdate();
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        ResultSet rs;

        try (Connection conn = Util.getConnection()) {
            if (Objects.nonNull(conn)) {
                Statement stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("name"));
                    user.setLastName(rs.getString("lastname"));
                    user.setAge(rs.getByte("age"));
                    users.add(user);
                }
                rs.close();
                stmt.close();
            }
            return users;
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
            return users;
        }
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users WHERE id > 0";
        try (Connection conn = Util.getConnection()) {
            if (Objects.nonNull(conn)) {
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
