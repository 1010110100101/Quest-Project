package DAL;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public User getUserByLoginAndPassword(String login, String password) {
        String sql = "SELECT id, login, password FROM Users WHERE login = ? AND password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Пользователь не найден
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT id, login, password FROM Users WHERE login = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Пользователь не найден
    }

    public boolean insertUser(User user) {
        String sql = "INSERT INTO Users (login, password) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}