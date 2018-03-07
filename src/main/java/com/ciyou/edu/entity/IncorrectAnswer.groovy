package com.ciyou.edu.entity

/**
 * @Author C.
 * @Date 2018-03-07 17:23
 */
class IncorrectAnswer implements Serializable{
    private static final long serialVersionUID = 1L
    private Integer incorrectNum
    private Integer question
    private Integer questionType

    Integer getIncorrectNum() {
        return incorrectNum
    }

    void setIncorrectNum(Integer incorrectNum) {
        this.incorrectNum = incorrectNum
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


    @Override
    public String toString() {
        return "IncorrectAnswer{" +
                "incorrectNum=" + incorrectNum +
                ", question=" + question +
                ", questionType=" + questionType +
                '}';
    }
}
