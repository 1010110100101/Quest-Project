package controller;

import model.services.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Проверяем, существует ли пользователь
        if (UserManager.isUserAlreadyExists(username)) {
            // Устанавливаем атрибут ошибки
            request.setAttribute("error", "<p style=\"color:red;\">Имя пользователя уже существует. Пожалуйста, выберите другое.</p>");
        } else {
            if (UserManager.insertNewUser(username, password)) {
                response.sendRedirect("login.jsp");
            } else {
                // Устанавливаем атрибут ошибки
                request.setAttribute("error", "<p style=\"color:red;\">Ошибка создания учетной записи. Повторите попытку позже.</p>");
            }
        }

        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}






    /*private static final String QUESTS_DIRECTORY = "quests";
    private static final String USER_DATA_FILE = QUESTS_DIRECTORY + "/users.txt";
    private static final List<User> users = new ArrayList<>();
    private int nextUserId;

    @Override
    public void init() throws ServletException {
        super.init();
        loadUsersFromFile();
        updateNextUserId();
    }

    private void updateNextUserId() {
        nextUserId = users.stream()
                .mapToInt(User::getId)
                .max()
                .orElse(0) + 1;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (users.stream().anyMatch(user -> user.getUsername().equals(username))) {
            response.sendRedirect("register.jsp?error=1");
        } else {
            saveUserToFile(username, password);
            response.sendRedirect("login.jsp");
        }
    }

    private void saveUserToFile(String username, String password) {
        users.add(new User(nextUserId, username, password));
        saveUsersToFile();
        nextUserId++;
    }

    public static User getUserByLoginAndPassword(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public void loadUsersFromFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(USER_DATA_FILE).getFile());

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        int userId = Integer.parseInt(parts[0].trim());
                        String username = parts[1].trim();
                        String userPassword = parts[2].trim();
                        users.add(new User(userId, username, userPassword));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE))) {
            for (User user : users) {
                writer.write(user.getId() + "," + user.getUsername() + "," + user.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/
