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
    //任课老师
    private List<Teacher> teacher

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

    List<Teacher> getTeacher() {
        return teacher
    }

    void setTeacher(List<Teacher> teacher) {
        this.teacher = teacher
    }


    @Override
    public String toString() {
        return "Classes{" +
                "classesId=" + classesId +
                ", grade=" + grade +
                ", classes=" + classes +
                ", teacher=" + teacher +
                '}';
    }
}
