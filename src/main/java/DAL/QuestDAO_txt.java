package DAL;

import model.Quest;
import model.Question;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class QuestDAO_txt {
    private static final String QUESTS_DIRECTORY = "quests";

    public QuestDAO_txt() {
        ClassLoader classLoader = getClass().getClassLoader();
        File dir = new File(classLoader.getResource(QUESTS_DIRECTORY).getFile());
        if (!dir.exists()) dir.mkdir();
    }

    public List<Quest> loadAllQuests() {
        List<Quest> quests = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        File[] files = new File(classLoader.getResource(QUESTS_DIRECTORY).getFile())
                .listFiles((d, name) -> name.endsWith(".txt"));

        if (files != null) {
            for (File file : files) {
                if (file.getName().startsWith("users")) {
                    continue;
                }
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
                    Quest quest = parseQuest(reader);
                    if (quest != null) {
                        quests.add(quest);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return quests;
    }

    public Quest loadQuestById(int questId) {
        ClassLoader classLoader = getClass().getClassLoader();
        File questFile = new File(classLoader.getResource(QUESTS_DIRECTORY).getFile(), questId + ".txt");

        if (questFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(questFile), StandardCharsets.UTF_8))) {
                return parseQuest(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void saveQuestToFile(Quest quest) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(QUESTS_DIRECTORY).getFile(), quest.getId() + ".txt");

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            writer.write("Quest ID: " + quest.getId() + "\n");
            writer.write("Author: " + quest.getAuthorId() + "\n");
            writer.write("Quest Name: " + quest.getName() + "\n");

            int questionNumber = 1;
            for (Question question : quest.getQuestions()) {
                writer.write("Question " + questionNumber + ": " + question.getText() + "\n");
                int optionNumber = 1;
                for (String option : question.getAnswers()) {
                    writer.write("Option " + optionNumber + ": " + option + "\n");
                    optionNumber++;
                }
                writer.write("Correct Answer: " + (question.getCorrectAnswerIndex() + 1) + "\n");
                writer.write("---\n");
                questionNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Quest parseQuest(BufferedReader reader) throws IOException {
        String line;
        int id = -1;
        String name = null;
        int authorId = -1;
        List<Question> questions = new ArrayList<>();

        line = reader.readLine();
        if (line != null && line.startsWith("Quest ID: ")) {
            id = Integer.parseInt(line.split(": ")[1]);
        }

        line = reader.readLine();
        if (line != null && line.startsWith("Author: ")) {
            authorId = Integer.parseInt(line.split(": ")[1]);
        }

        line = reader.readLine();
        if (line != null && line.startsWith("Quest Name: ")) {
            name = line.split(": ")[1];
        }

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Question")) {
                String questionText = line.split(": ")[1];
                List<String> options = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    line = reader.readLine();
                    if (line != null && line.startsWith("Option")) {
                        options.add(line.split(": ")[1]);
                    }
                }
                int correctAnswer = Integer.parseInt(reader.readLine().split(": ")[1]) - 1;
                questions.add(new Question(questionText, options, correctAnswer));
            }
        }

        return new Quest(id, authorId, name, questions);
    }
}
