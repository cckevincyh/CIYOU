package com.ciyou.edu.entity

/**
 * @Author C.
 * @Date 2018-03-01 21:08
 */
class ResultScore  implements Serializable{
    private static final long serialVersionUID = 1L
    private List<ChoiceAnswer> choiceAnswers
    private List<JudgeAnswer> judgeAnswers
    private Score score

    List<ChoiceAnswer> getChoiceAnswers() {
        return choiceAnswers
    }

    void setChoiceAnswers(List<ChoiceAnswer> choiceAnswers) {
        this.choiceAnswers = choiceAnswers
    }

    List<JudgeAnswer> getJudgeAnswers() {
        return judgeAnswers
    }

    void setJudgeAnswers(List<JudgeAnswer> judgeAnswers) {
        this.judgeAnswers = judgeAnswers
    }

    Score getScore() {
        return score
    }

    void setScore(Score score) {
        this.score = score
    }


    @Override
    public String toString() {
        return "ResultScore{" +
                "choiceAnswers=" + choiceAnswers +
                ", judgeAnswers=" + judgeAnswers +
                ", score=" + score +
                '}';
    }
}
