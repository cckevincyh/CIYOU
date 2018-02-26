package com.ciyou.edu.entity

/**
 * @Author C.
 * @Date 2018-02-24 22:44
 */
class Quiz implements Serializable{
    private static final long serialVersionUID = 1L
    //编号(自增长)
    private Integer quizId
    //小测名称
    private String quizName
    //小测时间
    private Integer quizTime
    //选择题个数
    private Integer choiceNum
    //判断题个数
    private Integer judgeNum
    //选择题得分
    private Integer choiceScore
    //判断题得分
    private Integer judgeScore
    //总得分
    private Integer allScore
    //选择题
    private Set<Choice> choices
    //判断题
    private Set<Judge> judges
    //科目
    private Subject subject
    //年级
    private Grade grade
    //出题教师
    private Teacher teacher


    Integer getQuizId() {
        return quizId
    }

    void setQuizId(Integer quizId) {
        this.quizId = quizId
    }

    String getQuizName() {
        return quizName
    }

    void setQuizName(String quizName) {
        this.quizName = quizName
    }

    Integer getQuizTime() {
        return quizTime
    }

    void setQuizTime(Integer quizTime) {
        this.quizTime = quizTime
    }

    Integer getChoiceNum() {
        return choiceNum
    }

    void setChoiceNum(Integer choiceNum) {
        this.choiceNum = choiceNum
    }

    Integer getJudgeNum() {
        return judgeNum
    }

    void setJudgeNum(Integer judgeNum) {
        this.judgeNum = judgeNum
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

    Set<Choice> getChoices() {
        return choices
    }

    void setChoices(Set<Choice> choices) {
        this.choices = choices
    }

    Set<Judge> getJudges() {
        return judges
    }

    void setJudges(Set<Judge> judges) {
        this.judges = judges
    }

    Subject getSubject() {
        return subject
    }

    void setSubject(Subject subject) {
        this.subject = subject
    }

    Grade getGrade() {
        return grade
    }

    void setGrade(Grade grade) {
        this.grade = grade
    }

    Teacher getTeacher() {
        return teacher
    }

    void setTeacher(Teacher teacher) {
        this.teacher = teacher
    }


    @Override
    public String toString() {
        return "Quiz{" +
                "quizId=" + quizId +
                ", quizName='" + quizName + '\'' +
                ", quizTime=" + quizTime +
                ", choiceNum=" + choiceNum +
                ", judgeNum=" + judgeNum +
                ", choiceScore=" + choiceScore +
                ", judgeScore=" + judgeScore +
                ", allScore=" + allScore +
                ", choices=" + choices +
                ", judges=" + judges +
                ", subject=" + subject +
                ", grade=" + grade +
                ", teacher=" + teacher +
                '}';
    }
}
