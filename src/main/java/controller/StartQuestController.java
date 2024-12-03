package controller;

import model.Quest;
import model.Question;
import model.services.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/startQuest")
public class StartQuestController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Проверяем авторизацию пользователя
        if (!UserManager.checkAuthorization(request, response)) {
            return; // Завершаем выполнение, если пользователь не авторизован
        }

        HttpSession session = request.getSession();
        Quest quest = (Quest) session.getAttribute("quest");

        // Проверяем актуальность квеста
        if (quest == null) {
            showMessage(response, "Квест не найден.");
            return;
        }

        // Получаем текущий индекс вопроса
        Integer currentQuestionIndex = (Integer) session.getAttribute("currentQuestionIndex");
        if (currentQuestionIndex == null) {
            currentQuestionIndex = 0;
        }

        // Получаем текущий вопрос
        Question question = quest.getQuestions().get(currentQuestionIndex);

        // Устанавливаем атрибуты для отображения в JSP
        request.setAttribute("quest", quest);
        request.setAttribute("currentQuestionIndex", currentQuestionIndex);
        request.setAttribute("question", question);

        // Перенаправляем на страницу с вопросом
        request.getRequestDispatcher("/startQuest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Проверяем авторизацию пользователя
        if (!UserManager.checkAuthorization(request, response)) {
            return; // Завершаем выполнение, если пользователь не авторизован
        }

        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        Quest quest = (Quest) session.getAttribute("quest");

        // Проверяем актуальность квеста
        if (quest == null) {
            showMessage(response, "Квест не найден.");
            return;
        }

        // Получаем текущий индекс вопроса
        Integer currentQuestionIndex = (Integer) session.getAttribute("currentQuestionIndex");
        if (currentQuestionIndex == null) {
            currentQuestionIndex = 0;
        }

        // Проверяем, был ли отправлен ответ
        String selectedAnswerIndexStr = request.getParameter("answer");
        if (selectedAnswerIndexStr != null) {
            int selectedAnswerIndex = Integer.parseInt(selectedAnswerIndexStr);

            // Получаем текущий вопрос
            Question currentQuestion = quest.getQuestions().get(currentQuestionIndex);

            // Проверяем правильность ответа
            if (selectedAnswerIndex == currentQuestion.getCorrectAnswerIndex()) {
                currentQuestionIndex++;

                if (currentQuestionIndex >= quest.getQuestions().size()) {
                    session.removeAttribute("currentQuestionIndex");
                    response.sendRedirect("result.jsp?success=true");
                    return;
                }
            } else {
                session.removeAttribute("currentQuestionIndex");
                response.sendRedirect("result.jsp?success=false");
                return;
            }
        }

        // Обновляем индекс текущего вопроса и перенаправляем на страницу с вопросом
        session.setAttribute("currentQuestionIndex", currentQuestionIndex);
        response.sendRedirect("startQuest");
    }

    private void showMessage(HttpServletResponse response, String message) throws IOException {
        response.getWriter().write("<script type='text/javascript'>");
        response.getWriter().write("alert('" + message + "');");
        response.getWriter().write("window.location.href = 'quests.jsp';");
        response.getWriter().write("</script>");
    }
}
