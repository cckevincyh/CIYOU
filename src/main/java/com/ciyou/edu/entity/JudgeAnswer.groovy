package com.ciyou.edu.entity

/**
 * @Author C.
 * @Date 2018-03-01 21:08
 */
class JudgeAnswer implements Serializable{
    private static final long serialVersionUID = 1L
    private Answer answer
    private Judge judge

    Answer getAnswer() {
        return answer
    }

    void setAnswer(Answer answer) {
        this.answer = answer
    }

    Judge getJudge() {
        return judge
    }

    void setJudge(Judge judge) {
        this.judge = judge
    }


    @Override
    public String toString() {
        return "JudgeAnswer{" +
                "answer=" + answer +
                ", judge=" + judge +
                '}';
    }
}
