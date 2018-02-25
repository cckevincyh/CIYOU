package com.ciyou.edu.entity

/**
 * @Author C.
 * @Date 2018-02-24 22:45
 */
class Judge implements Serializable{
    private static final long serialVersionUID = 1L
    private Integer judgeId
    private Quiz quiz
    private String question
    private String answer

    Integer getJudgeId() {
        return judgeId
    }

    void setJudgeId(Integer judgeId) {
        this.judgeId = judgeId
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

    String getAnswer() {
        return answer
    }

    void setAnswer(String answer) {
        this.answer = answer
    }


    @Override
    public String toString() {
        return "Judge{" +
                "judgeId=" + judgeId +
                ", quiz=" + quiz +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
