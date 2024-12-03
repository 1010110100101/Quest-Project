package model;

import model.services.QuestManage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class QuestManageTest {

    @Test
    void test_GetAllQuestsByQuestManage() {
        QuestManage questManage = new QuestManage();
        List<Quest> quests = questManage.getAllQuests();
        assertNotNull(quests, "Список квестов не должен быть null");
        assertFalse(quests.isEmpty(), "Список квестов не должен быть пустым");
    }

    @Test
    void test_ParseQuestAndCheckNotNull() {
        QuestManage questManage = new QuestManage();
        Quest quest = questManage.getQuestById(1);
        assertNotNull(quest, "Квест не должен быть null");
        assertEquals("Бабушкин квест", quest.getName(), "Название квеста не совпадает");
    }

    @Test
    void test_TryToGetQuestByInvalidId() {
        QuestManage questManage = new QuestManage();
        Quest quest = questManage.getQuestById(-8);
        assertNull(quest, "Квест должен быть null при невалидном файле");
    }
}
