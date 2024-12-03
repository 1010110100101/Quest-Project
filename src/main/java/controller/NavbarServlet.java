package controller;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/navbar")
public class NavbarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Логика хлебных крошек
        @SuppressWarnings("unchecked")
        List<String> breadcrumbs = (List<String>) session.getAttribute("breadcrumbs");
        if (breadcrumbs == null) {
            breadcrumbs = new ArrayList<>();
            session.setAttribute("breadcrumbs", breadcrumbs);
        }

        String currentPage = request.getRequestURI();
        String currentPageName = currentPage.substring(currentPage.lastIndexOf("/") + 1);
        if (!breadcrumbs.contains(currentPageName) && !currentPageName.equals("index.jsp")) {
            breadcrumbs.add(currentPageName);
        }

        // Формируем HTML хлебных крошек
        String breadcrumbsHtml = generateBreadcrumbsHtml(breadcrumbs);
        request.setAttribute("breadcrumbsHtml", breadcrumbsHtml);

        // Логика пользователя
        User user = (User) session.getAttribute("user");
        if (user != null) {
            request.setAttribute("username", user.getUsername());
        }

        // Перенаправление на JSP-шаблон
        request.getRequestDispatcher("/navbar.jsp").include(request, response);
    }

    /**
     * Генерация HTML для хлебных крошек.
     *
     * @param breadcrumbs Список страниц
     * @return HTML строка
     */
    private String generateBreadcrumbsHtml(List<String> breadcrumbs) {
        StringBuilder breadcrumbsBuilder = new StringBuilder();
        breadcrumbsBuilder.append("<a href=\"index.jsp\">Home</a>");

        for (int i = 0; i < breadcrumbs.size(); i++) {
            String page_ = breadcrumbs.get(i);
            String pageName = getPageName(page_);

            // Пропускаем пустые крошки
            if (pageName.isEmpty()) {
                continue;
            }

            // Если это последний элемент, то отображаем его без ссылки
            if (i == breadcrumbs.size() - 1) {
                breadcrumbsBuilder.append(" &gt; <span>").append(pageName).append("</span>");
            } else {
                breadcrumbsBuilder.append(" &gt; <a href=\"").append(page_).append("\">").append(pageName).append("</a>");
            }
        }

        return breadcrumbsBuilder.toString();
    }

    /**
     * Получение читаемого имени страницы.
     *
     * @param page Имя страницы
     * @return Название страницы
     */
    private String getPageName(String page) {
        return switch (page) {
            case "quests.jsp" -> "Quests";
            case "createQuest.jsp" -> "Create quest";
            case "login.jsp" -> "Login";
            case "register.jsp" -> "Register";
            case "quest.jsp" -> "Quest";
            case "welcome.jsp" -> "Welcome";
            default -> "";
        };
    }
}
