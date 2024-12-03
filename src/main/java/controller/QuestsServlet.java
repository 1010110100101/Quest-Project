package controller;

import model.Quest;
import model.services.QuestManage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/quests")
public class QuestsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем список квестов через QuestManage
        QuestManage questManage = new QuestManage();
        List<Quest> quests = questManage.getAllQuests();

        // Генерируем HTML для списка квестов
        String questsHtml = generateQuestsHtml(quests);

        // Передаём сгенерированный HTML и количество квестов в JSP
        request.setAttribute("questsHtml", questsHtml);
        request.setAttribute("totalQuests", (quests != null) ? quests.size() : 0);

        // Перенаправляем на JSP
        request.getRequestDispatcher("/quests.jsp").forward(request, response);
    }

    /**
     * Генерирует HTML-код для списка квестов.
     *
     * @param quests список квестов
     * @return строка с HTML-кодом
     */
    private String generateQuestsHtml(List<Quest> quests) {
        if (quests == null || quests.isEmpty()) {
            return "<p>Квесты не доступны.</p>";
        }

        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<div style='margin-left: -40px;'>");
        for (Quest quest : quests) {
            htmlBuilder.append("<a href='quest?questid=")
                    .append(quest.getId())
                    .append("' class='questBlock' title='Нажмите для выбора квеста'")
                    .append(quest.getName())
                    .append("\">")
                    .append("<div class='innerQuestDiv'>")
                    .append("<h3>")
                    .append(quest.getName())
                    .append("</h3>")
                    .append("<p>Вопросов: ")
                    .append(quest.getQuestionCount())
                    .append("</p>")
                    .append("<p>Автор: ")
                    .append(quest.getAuthorName())
                    .append("</p>")
                    .append("</div>")
                    .append("</a>");
        }
        htmlBuilder.append("</div>");
        return htmlBuilder.toString();
    }
}
