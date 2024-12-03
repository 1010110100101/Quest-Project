// model/Quest.java
package model;

import java.util.List;

public class Quest {
    private int id;
    private String name;
    private int authorId;
    private String authorName;
    private List<Question> questions;
    private int questionCount;


    public Quest(int id, int authorId, List<Question> questions) {
        this.id = id;
        this.authorId = authorId;
        this.questions = questions;
    }

    public Quest(int id, String authorName, String title, List<Question> questions) {
        this.id = id;
        this.authorName = authorName;
        this.name = title;
        this.questions = questions;
    }


    public Quest(int id, int authorId, String name, List<Question> questions) {
        this.id = id;
        this.authorId = authorId;
        this.name = name;
        this.questions = questions;
    }

    public Quest(int id, String authorName, String title, int questionCount) {
        this.id = id;
        this.authorName = authorName;
        this.name = title;
        this.questionCount = questionCount;
    }

    public String getName() {
        return name;
    }

    public int getAuthorId() {
        return authorId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public int getQuestionCount() {
        if(this.questions == null || this.questions.isEmpty()) {
            return questionCount;
        }
        return questions.size();
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setAuthorId(int authorId) { this.authorId = authorId; }

    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public void setQuestions(List<Question> questions) { this.questions = questions; }

    public void setQuestionCount(int questionCount) { this.questionCount = questionCount; }

    public String getAuthorName() {
        return this.authorName;
    }
}
