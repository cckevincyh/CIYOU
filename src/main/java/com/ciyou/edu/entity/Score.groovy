package com.ciyou.edu.entity

/**
 * @Author C.
 * @Date 2018-03-01 11:50
 */
class Score implements Serializable{
    private static final long serialVersionUID = 1L
    private Integer scoreId
    private Student student
    private Quiz quiz
    private Integer choiceScore
    private Integer judgeScore
    private Integer allScore

    Integer getScoreId() {
        return scoreId
    }

    void setScoreId(Integer scoreId) {
        this.scoreId = scoreId
    }

    Student getStudent() {
        return student
    }

    void setStudent(Student student) {
        this.student = student
    }

    Quiz getQuiz() {
        return quiz
    }

    void setQuiz(Quiz quiz) {
        this.quiz = quiz
    }

    Integer getChoiceScore() {
        return choiceScore
    }

    void setChoiceScore(Integer choiceScore) {
        this.choiceScore = choiceScore
    }

    Integer getJudgeScore() {
        return judgeScore
    }

    void setJudgeScore(Integer judgeScore) {
        this.judgeScore = judgeScore
    }

    Integer getAllScore() {
        return allScore
    }

    void setAllScore(Integer allScore) {
        this.allScore = allScore
    }


    @Override
    public String toString() {
        return "Score{" +
                "scoreId=" + scoreId +
                ", student=" + student +
                ", quiz=" + quiz +
                ", choiceScore=" + choiceScore +
                ", judgeScore=" + judgeScore +
                ", allScore=" + allScore +
                '}';
    }
}
