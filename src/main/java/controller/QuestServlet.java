package controller;

import model.Quest;
import model.services.QuestManage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/quest")
public class QuestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем ID квеста из параметра запроса
        String questIdParam = request.getParameter("questid");
        if (questIdParam == null || questIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Quest ID is missing");
            return;
        }

        int questId;
        try {
            questId = Integer.parseInt(questIdParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Quest ID");
            return;
        }

        // Получаем квест через модель
        QuestManage questManage = new QuestManage();
        Quest quest = questManage.getQuestById(questId);

        if (quest == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Quest not found");
            return;
        }

        // Передаем квест в запрос
        request.setAttribute("quest", quest);

        // Форвардим запрос на JSP
        request.getRequestDispatcher("quest.jsp").forward(request, response);
    }
}
