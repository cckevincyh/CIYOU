package com.ciyou.edu.entity

/**
 * @Author C.
 * @Date 2018-02-23 21:33
 */
class Roster implements Serializable{
    private static final long serialVersionUID = 1L

    private Integer rid
    private Teacher teacher
    private Classes classes
    private Subject subject

    Integer getRid() {
        return rid
    }

    void setRid(Integer rid) {
        this.rid = rid
    }

    Teacher getTeacher() {
        return teacher
    }

    void setTeacher(Teacher teacher) {
        this.teacher = teacher
    }

    Classes getClasses() {
        return classes
    }

    void setClasses(Classes classes) {
        this.classes = classes
    }

    Subject getSubject() {
        return subject
    }

    void setSubject(Subject subject) {
        this.subject = subject
    }


    @Override
    public String toString() {
        return "Roster{" +
                "rid=" + rid +
                ", teacher=" + teacher +
                ", classes=" + classes +
                ", subject=" + subject +
                '}';
    }
}
