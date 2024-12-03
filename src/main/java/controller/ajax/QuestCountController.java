package controller.ajax;

import model.services.QuestManage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/getQuestCount")
public class QuestCountController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем id автора из параметров запроса
        String authorIdStr = request.getParameter("authorId");

        if (authorIdStr != null) {
            try {
                int authorId = Integer.parseInt(authorIdStr);

                // Получаем количество квестов для этого автора
                int questCount = QuestManage.getQuestCountByAuthorId(authorId);

                // Устанавливаем тип контента как текст
                response.setContentType("text/plain");
                response.getWriter().write(String.valueOf(questCount));
            } catch (SQLException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка при подключении к базе данных.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Не указан ID автора.");
        }
    }
}
