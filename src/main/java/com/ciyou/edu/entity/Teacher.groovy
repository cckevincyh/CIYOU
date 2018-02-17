package com.ciyou.edu.entity

/**
 * @Author C.
 * @Date 2018-02-10 9:20
 */
class Teacher implements Serializable{
    private static final long serialVersionUID = 1L

    //教师编号 （自增长）
    private Integer tid
    //教师号
    private String teacherId
    //真实姓名
    private String name
    //密码
	private String password
	//性别
    private Integer sex
    //创建时间
    private Date createTime
    //状态 1：正常 2：冻结
    private Integer isAvalible
    //联系方式
    private String mobile
    //email
    private String email
    //头像
    private String picImg
    //任课科目
    private Subject subject

    Integer getTid() {
        return tid
    }

    void setTid(Integer tid) {
        this.tid = tid
    }

    String getTeacherId() {
        return teacherId
    }

    void setTeacherId(String teacherId) {
        this.teacherId = teacherId
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

    Date getCreateTime() {
        return createTime
    }

    void setCreateTime(Date createTime) {
        this.createTime = createTime
    }

    Integer getIsAvalible() {
        return isAvalible
    }

    void setIsAvalible(Integer isAvalible) {
        this.isAvalible = isAvalible
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

    String getPicImg() {
        return picImg
    }

    void setPicImg(String picImg) {
        this.picImg = picImg
    }

    Subject getSubject() {
        return subject
    }

    void setSubject(Subject subject) {
        this.subject = subject
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "tid=" + tid +
                ", teacherId='" + teacherId + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", createTime=" + createTime +
                ", isAvalible=" + isAvalible +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", picImg='" + picImg + '\'' +
                ", subject=" + subject +
                '}';
    }
}
