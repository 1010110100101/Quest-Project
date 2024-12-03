package DAL;

import model.Quest;
import model.Question;

import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class QuestDAO {
    public Quest getQuestById(int questId) {
        String questSql = """
            SELECT q.id, q.title, u.id AS author_id, u.login AS author_name 
            FROM PresentsQuests q
            JOIN Users u ON q.author_id = u.id
            WHERE q.id = ?
            """;
        String stepsSql = """
            SELECT id, title, correct_answer_number, answers
            FROM QuestsSteps WHERE quest_id = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement questStatement = connection.prepareStatement(questSql);
             PreparedStatement stepsStatement = connection.prepareStatement(stepsSql)) {

            questStatement.setInt(1, questId);
            ResultSet questRs = questStatement.executeQuery();

            if (questRs.next()) {
                List<Question> questions = new ArrayList<>();
                stepsStatement.setInt(1, questId);
                ResultSet stepsRs = stepsStatement.executeQuery();

                while (stepsRs.next()) {
                    List<String> answers = List.of(stepsRs.getString("answers").split(";"));
                    Question question = new Question(
                            stepsRs.getString("title"),
                            answers,
                            stepsRs.getInt("correct_answer_number")
                    );
                    questions.add(question);
                }
                Quest quest = new Quest(
                        questRs.getInt("id"),
                        questRs.getString("author_name"),
                        questRs.getString("title"),
                        questions
                );
                quest.setAuthorId(questRs.getInt("author_id"));

                return quest;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Quest> getAllQuests() {
        String sql = """
            SELECT q.id, q.title, u.id AS author_id, u.login AS author_name, 
                   (SELECT COUNT(*) FROM QuestsSteps WHERE quest_id = q.id) AS question_count
            FROM PresentsQuests q
            JOIN Users u ON q.author_id = u.id
            """;
        List<Quest> quests = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Quest quest = new Quest(
                        rs.getInt("id"),
                        rs.getString("author_name"),
                        rs.getString("title"),
                        rs.getInt("question_count")
                );
                quest.setAuthorId(rs.getInt("author_id"));
                quests.add(quest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quests;
    }

    public boolean insertQuest(Quest quest) {
        String questSql = "INSERT INTO PresentsQuests (title, author_id) VALUES (?, ?)";
        String stepsSql = "INSERT INTO QuestsSteps (quest_id, title, correct_answer_number, answers) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement questStatement = connection.prepareStatement(questSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                questStatement.setString(1, quest.getName());
                questStatement.setInt(2, quest.getAuthorId());
                questStatement.executeUpdate();

                ResultSet generatedKeys = questStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int questId = generatedKeys.getInt(1);

                    try (PreparedStatement stepsStatement = connection.prepareStatement(stepsSql)) {
                        for (Question question : quest.getQuestions()) {
                            stepsStatement.setInt(1, questId);
                            stepsStatement.setString(2, question.getText());
                            stepsStatement.setInt(3, question.getCorrectAnswerIndex());
                            stepsStatement.setString(4, String.join(";", question.getAnswers()));
                            stepsStatement.addBatch();
                        }
                        stepsStatement.executeBatch();

                    }
                }
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                e.printStackTrace();

                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    // Метод для получения количества квестов конкретного автора по его id
    public int getQuestCountByAuthorId(int authorId) throws SQLException {
        int count = 0;

        // Строка подключения к базе данных
        String query = "SELECT COUNT(*) FROM PresentsQuests WHERE author_id = ?";

        // Получаем соединение с базой данных
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Устанавливаем значение параметра авторского ID
            statement.setInt(1, authorId);

            // Выполняем запрос
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Получаем количество квестов
                    count = resultSet.getInt(1);
                }
            }
        }

        return count;
    }
}
