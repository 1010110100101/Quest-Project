package controller;

import model.Question;
import model.Quest;
import model.User;
import model.services.QuestManage;
import model.services.UserManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/createQuest")
public class CreateQuestController extends HttpServlet {
    private QuestManage questManage;

    @Override
    public void init() {
        questManage = new QuestManage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Проверка на авторизацию
        User user = UserManager.getUserFromSession(request);
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Отправка данных для отображения формы
        request.getRequestDispatcher("/createQuest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");  // Устанавливаем кодировку

        // Проверка на авторизацию
        User user = UserManager.getUserFromSession(request);
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int authorId = user.getId();
        String questName = request.getParameter("questName");
        int questionCount = Integer.parseInt(request.getParameter("questionCount"));

        // Создание квеста
        Quest quest = prepareQuest(request, questionCount, authorId, questName);

        if (questManage.addQuest(quest)) {
            response.sendRedirect("quests");
        } else {
            showMessage(response, "Квест не создан. Повторите попытку позже");
        }
    }

    private void showMessage(HttpServletResponse response, String message) throws IOException {
        response.getWriter().write("<script type='text/javascript'>");
        response.getWriter().write("alert('" + message + "');");
        response.getWriter().write("window.location.href = 'createQuest.jsp';");
        response.getWriter().write("</script>");
    }

    private Quest prepareQuest(HttpServletRequest request, int questionCount, int authorId, String questName) {
        List<Question> questions = new ArrayList<>();
        for (int i = 1; i <= questionCount; i++) {
            String text = request.getParameter("question" + i);
            String[] options = {
                    request.getParameter("option" + i + "_1").replace(";", "&#59;"),
                    request.getParameter("option" + i + "_2").replace(";", "&#59;"),
                    request.getParameter("option" + i + "_3").replace(";", "&#59;"),
                    request.getParameter("option" + i + "_4").replace(";", "&#59;")
            };
            int correctAnswerIndex = Integer.parseInt(request.getParameter("correctAnswer" + i)) - 1;
            questions.add(new Question(text, List.of(options), correctAnswerIndex));
        }

        return new Quest(0, authorId, questName, questions);
    }
}
