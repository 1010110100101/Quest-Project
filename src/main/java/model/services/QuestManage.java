package model.services;

import DAL.QuestDAO;
import model.Quest;

import java.sql.SQLException;
import java.util.List;

public class QuestManage {

    private final static QuestDAO questDAO = new QuestDAO();


    public static int getQuestCountByAuthorId(int authorId) throws SQLException {
        if(authorId <= 0) {
            return 0;
        }
        return questDAO.getQuestCountByAuthorId(authorId);
    }

    /**
     * Получить все квесты из базы данных.
     * @return список всех квестов
     */
    public List<Quest> getAllQuests() {
        return questDAO.getAllQuests();
    }

    /**
     * Добавить новый квест в базу данных.
     * @param quest объект квеста
     */
    public boolean addQuest(Quest quest) {
        return questDAO.insertQuest(quest);
    }

    /**
     * Получить квест по его ID.
     * @param questId ID квеста
     * @return объект квеста или null, если квест не найден
     */
    public Quest getQuestById(int questId) {
        Quest quest = questDAO.getQuestById(questId);
        return quest;
    }

    /**
     * Обновить информацию о квесте в базе данных.
     * @param quest обновленный объект квеста
     */
    public void updateQuest(Quest quest) {
        //questDAO.updateQuest(quest);
    }

    /**
     * Удалить квест по его ID.
     * @param questId ID квеста
     */
    public void deleteQuest(int questId) {
        //questDAO.deleteQuest(questId);
    }
}