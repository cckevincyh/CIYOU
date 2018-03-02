package com.ciyou.edu.entity

/**
 * @Author C.
 * @Date 2018-03-01 21:08
 */
class ChoiceAnswer implements Serializable{
    private static final long serialVersionUID = 1L
    private Answer answer
    private Choice choice

    Answer getAnswer() {
        return answer
    }

    void setAnswer(Answer answer) {
        this.answer = answer
    }

    Choice getChoice() {
        return choice
    }

    void setChoice(Choice choice) {
        this.choice = choice
    }


    @Override
    public String toString() {
        return "ChoiceAnswer{" +
                "answer=" + answer +
                ", choice=" + choice +
                '}';
    }
}
