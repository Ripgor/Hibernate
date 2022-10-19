package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection connection = Util.getConnect();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        final String request = """
                CREATE TABLE if not exists users (
                  id int AUTO_INCREMENT,
                  name varchar(45) NOT NULL,
                  lastname varchar(45) NOT NULL,
                  age int NOT NULL,
                  PRIMARY KEY (id)
                );""";

        try (Statement statement = connection.createStatement()) {
            statement.execute(request);
        } catch (SQLException e) { }
    }

    public void dropUsersTable() {
        final String request = "DROP TABLE IF EXISTS users;";

        try (Statement statement = connection.createStatement()) {
            statement.execute(request);
        } catch (SQLException e) { }
    }

    public void saveUser(String name, String lastName, byte age) {
        final String request = "INSERT INTO users(name, lastname, age) values(?, ?, ?);";

        try (PreparedStatement ps = connection.prepareStatement(request)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);

            ps.executeUpdate();
            System.out.printf("User с именем %s добавлен в базу данных\n", name);
        } catch (SQLException e) { }
    }

    public void removeUserById(long id) {
        final String request = "DELETE FROM users where id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(request)) {
            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) { }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        final String request = "SELECT * FROM users;";

        try (Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery(request);

            while (res.next()) {
                User user = new User();
                user.setId(res.getLong("id"));
                user.setName(res.getString("name"));
                user.setLastName(res.getString("lastname"));
                user.setAge(res.getByte("age"));

                users.add(user);
            }
        } catch (SQLException e) { }

        return users;
    }

    public void cleanUsersTable() {
        final String request = "TRUNCATE users;";

        try (Statement statement = connection.createStatement()) {
            statement.execute(request);
        } catch (SQLException e) { }
    }
}