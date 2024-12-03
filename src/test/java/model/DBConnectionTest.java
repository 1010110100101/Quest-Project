package model;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static DAL.DatabaseConnection.getConnection;
import static org.junit.jupiter.api.Assertions.*;

public class DBConnectionTest {

    @Test
    void test_GetSQLConnectionIsCanCreated() {
        try (Connection connection = getConnection()) {
            // Проверяем, что соединение не null
            assertNotNull(connection, "Соединение с базой данных не установлено.");

            // Проверяем, что соединение активно
            assertFalse(connection.isClosed(), "Соединение с базой данных было закрыто.");
        } catch (SQLException e) {
            // Тест провален, если выбрасывается исключение
            fail("Ошибка при подключении к базе данных: " + e.getMessage());
        }
    }
}
