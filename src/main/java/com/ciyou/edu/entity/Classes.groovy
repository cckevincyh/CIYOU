package com.ciyou.edu.entity

/**
 * @Author C.
 * @Date 2018-02-02 15:36
 */
class Classes implements Serializable{
    private static final long serialVersionUID = 1L

    //班级编号（自增长）
    private Integer classesId
    //年级
    private Grade grade
    //几班
    private Integer classes

    Integer getClassesId() {
        return classesId
    }

    void setClassesId(Integer classesId) {
        this.classesId = classesId
    }

    Grade getGrade() {
        return grade
    }

    void setGrade(Grade grade) {
        this.grade = grade
    }

    Integer getClasses() {
        return classes
    }

    void setClasses(Integer classes) {
        this.classes = classes
    }


    @Override
    public String toString() {
        return "Classes{" +
                "classesId=" + classesId +
                ", grade=" + grade +
                ", classes=" + classes +
                '}';
    }
}
