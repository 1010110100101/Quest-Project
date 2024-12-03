// model/Question.java
package model;

import java.util.List;

public class Question {
    private String text;
    private List<String> answers;
    private int correctAnswerIndex;

    public Question(String text, List<String> answers, int correctAnswerIndex) {
        this.text = text;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getText() {
        return this.text;
    }

    public List<String> getAnswers() {
        return this.answers;
    }

    public int getCorrectAnswerIndex() {
        return this.correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public boolean isCorrect(int answerIndex) {
        return answerIndex == this.correctAnswerIndex;
    }

    public String getCorrectAnswer() {
        return this.answers.get(this.correctAnswerIndex);
    }
}
