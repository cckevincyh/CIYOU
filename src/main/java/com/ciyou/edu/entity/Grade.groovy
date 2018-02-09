package com.ciyou.edu.entity

/**
 * @Author C.
 * @Date 2018-02-09 17:50
 */
class Grade implements Serializable{
    private static final long serialVersionUID = 1L

    //年级编号（自增长）
    private Integer gradeId
    //几年级
    private String gradeName

    Integer getGradeId() {
        return gradeId
    }

    void setGradeId(Integer gradeId) {
        this.gradeId = gradeId
    }

    String getGradeName() {
        return gradeName
    }

    void setGradeName(String gradeName) {
        this.gradeName = gradeName
    }


    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", gradeName='" + gradeName + '\'' +
                '}';
    }
}
