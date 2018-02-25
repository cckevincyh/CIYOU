package com.ciyou.edu.entity

/**
 * @Author C.
 * @Date 2018-02-24 22:45
 */
class Choice implements Serializable{
    private static final long serialVersionUID = 1L
    private Integer choiceId
    private Quiz quiz
    private String question
    private String optionA
    private String optionB
    private String optionC
    private String optionD
    private String answer

    Integer getChoiceId() {
        return choiceId
    }

    void setChoiceId(Integer choiceId) {
        this.choiceId = choiceId
    }

    Quiz getQuiz() {
        return quiz
    }

    void setQuiz(Quiz quiz) {
        this.quiz = quiz
    }

    String getQuestion() {
        return question
    }

    void setQuestion(String question) {
        this.question = question
    }

    String getOptionA() {
        return optionA
    }

    void setOptionA(String optionA) {
        this.optionA = optionA
    }

    String getOptionB() {
        return optionB
    }

    void setOptionB(String optionB) {
        this.optionB = optionB
    }

    String getOptionC() {
        return optionC
    }

    void setOptionC(String optionC) {
        this.optionC = optionC
    }

    String getOptionD() {
        return optionD
    }

    void setOptionD(String optionD) {
        this.optionD = optionD
    }

    String getAnswer() {
        return answer
    }

    void setAnswer(String answer) {
        this.answer = answer
    }


    @Override
    public String toString() {
        return "Choice{" +
                "choiceId=" + choiceId +
                ", quiz=" + quiz +
                ", question='" + question + '\'' +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", optionD='" + optionD + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
