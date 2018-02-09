package com.ciyou.edu.entity

/**
 * @Author C.
 * @Date 2018-02-09 22:51
 */
class Subject implements Serializable{
    private static final long serialVersionUID = 1L

    //科目编号（自增长）
    private Integer subjectId
    //科目名
    private String subjectName

    Integer getSubjectId() {
        return subjectId
    }

    void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId
    }

    String getSubjectName() {
        return subjectName
    }

    void setSubjectName(String subjectName) {
        this.subjectName = subjectName
    }


    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }
}
