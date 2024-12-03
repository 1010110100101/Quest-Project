// controller/LoginController.java
package controller;

import model.User;
import model.services.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Проверка учетных данных через UserDAO
        User user = UserManager.getUserByLoginAndPassword(username, password);
        if (user != null) {
            // Создание сессии для авторизованного пользователя
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(20 * 60); // Тайм-аут сессии: 20 минут
            response.sendRedirect("welcome.jsp");
        } else {
            // Ошибка авторизации
            response.sendRedirect("login.jsp?error=1");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Завершение сессии при запросе на logout
        String logout = request.getParameter("logout");
        if ("true".equals(logout)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("login.jsp");
        }
    }


/*    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RegisterController registerController = new RegisterController();
        registerController.loadUsersFromFile(); // Подгрузить пользователей из файла, если они еще не загружены

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Проверка учетных данных
        User user = RegisterController.getUserByLoginAndPassword(username, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(20 * 60); // Тайм-аут сессии: 20 минут
            response.sendRedirect("welcome.jsp");
        } else {
            response.sendRedirect("login.jsp?error=1");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Завершение сессии при запросе на logout
        String logout = request.getParameter("logout");
        if (logout != null && logout.equals("true")) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("login.jsp");
        }
    }*/
}
