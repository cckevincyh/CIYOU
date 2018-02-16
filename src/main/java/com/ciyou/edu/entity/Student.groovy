package com.ciyou.edu.entity

/**
 * @Author C.
 * @Date 2018-02-02 15:35
 */
class Student implements Serializable{
    private static final long serialVersionUID = 1L
    //学生编号 （自增长）
    private Integer sid
    //学号（唯一）
    private String studentId
    //真实姓名
    private String name
    //密码
    private String password
    //性别
    private Integer sex
    //年龄
    private Integer age
    //创建时间
    private Date createTime
    //父母联系方式
    private String parentMobile
    //父母email
    private String parentEmail
    //个人联系方式
    private String mobile
    //个人email
    private String email
    //学生头像
    private String picImg
    //对应班级表
    private Classes classes
    //状态 1：正常 2：冻结
    private Integer isAvalible

    Integer getSid() {
        return sid
    }

    void setSid(Integer sid) {
        this.sid = sid
    }

    String getStudentId() {
        return studentId
    }

    void setStudentId(String studentId) {
        this.studentId = studentId
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getPassword() {
        return password
    }

    void setPassword(String password) {
        this.password = password
    }

    Integer getSex() {
        return sex
    }

    void setSex(Integer sex) {
        this.sex = sex
    }

    Integer getAge() {
        return age
    }

    void setAge(Integer age) {
        this.age = age
    }

    Date getCreateTime() {
        return createTime
    }

    void setCreateTime(Date createTime) {
        this.createTime = createTime
    }

    String getParentMobile() {
        return parentMobile
    }

    void setParentMobile(String parentMobile) {
        this.parentMobile = parentMobile
    }

    String getParentEmail() {
        return parentEmail
    }

    void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail
    }

    String getMobile() {
        return mobile
    }

    void setMobile(String mobile) {
        this.mobile = mobile
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }

    Classes getClasses() {
        return classes
    }

    void setClasses(Classes classes) {
        this.classes = classes
    }


    Integer getIsAvalible() {
        return isAvalible
    }

    void setIsAvalible(Integer isAvalible) {
        this.isAvalible = isAvalible
    }

    String getPicImg() {
        return picImg
    }

    void setPicImg(String picImg) {
        this.picImg = picImg
    }


    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", createTime=" + createTime +
                ", parentMobile='" + parentMobile + '\'' +
                ", parentEmail='" + parentEmail + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", picImg='" + picImg + '\'' +
                ", classes=" + classes +
                ", isAvalible=" + isAvalible +
                '}';
    }
}
