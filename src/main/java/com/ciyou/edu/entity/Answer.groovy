package com.ciyou.edu.entity

/**
 * @Author C.
 * @Date 2018-03-01 11:48
 */
class Answer implements Serializable{
    private static final long serialVersionUID = 1L
    private Integer answerId
    private Student student
    private Quiz quiz
    private Integer question
    private Integer questionType
    private String answer
    private String goodAnswer
    private Integer score

    Integer getAnswerId() {
        return answerId
    }

    void setAnswerId(Integer answerId) {
        this.answerId = answerId
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

    Integer getQuestion() {
        return question
    }

    void setQuestion(Integer question) {
        this.question = question
    }

    Integer getQuestionType() {
        return questionType
    }

    void setQuestionType(Integer questionType) {
        this.questionType = questionType
    }

    String getAnswer() {
        return answer
    }

    void setAnswer(String answer) {
        this.answer = answer
    }

    String getGoodAnswer() {
        return goodAnswer
    }

    void setGoodAnswer(String goodAnswer) {
        this.goodAnswer = goodAnswer
    }

    Integer getScore() {
        return score
    }

    void setScore(Integer score) {
        this.score = score
    }


    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", student=" + student +
                ", quiz=" + quiz +
                ", question=" + question +
                ", questionType=" + questionType +
                ", answer='" + answer + '\'' +
                ", goodAnswer='" + goodAnswer + '\'' +
                ", score=" + score +
                '}';
    }
}
