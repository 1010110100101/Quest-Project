package controller;

import model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/footer")
public class FooterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получение текущей сессии
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        // Устанавливаем атрибут для состояния авторизации
        request.setAttribute("isUserLoggedIn", user != null);
        request.setAttribute("user", user); // Передаем пользователя, если он авторизован

        // Передача ссылки на социальные сети
        request.setAttribute("telegramLink", "tg://resolve?domain=GazSto");
        request.setAttribute("viberLink", "viber://add?number=380682332895");
        request.setAttribute("whatsappLink", "https://wa.me/380662035793");

        // Переход на JSP
        request.getRequestDispatcher("footer.jsp").include(request, response);
    }
}

