package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/quests?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345";

    public static Connection getConnection() {
        try {
            // Загрузка драйвера MySQL
            //Class.forName("com.mysql.cj.jdbc.Driver");

            // Устанавливаем соединение
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // Проверка соединения
            /*if (connection != null) {
                System.out.println("Соединение с базой данных установлено!");
            }*/
            return connection;
        } catch (SQLException e) {
            //e.printStackTrace();
            return null;
        }
    }
}
