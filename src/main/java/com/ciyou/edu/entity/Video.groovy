package com.ciyou.edu.entity

/**
 * @Author C.
 * @Date 2018-02-20 17:49
 */
class Video implements Serializable{

    private static final long serialVersionUID = 1L
    //视频编号
    private Integer videoId
    //视频名称
    private String videoName
    //视频路径
    private String videoUrl
    //封面路径
    private String imgUrl
    //上传教师
    private Teacher teacher
    //科目
    private Subject subject
    //年级
    private Grade grade
    //创建时间
    private Date createTime
    //视频状态：审核状态，删除状态
    private Integer videoType

    Integer getVideoId() {
        return videoId
    }

    void setVideoId(Integer videoId) {
        this.videoId = videoId
    }

    String getVideoName() {
        return videoName
    }

    void setVideoName(String videoName) {
        this.videoName = videoName
    }

    String getVideoUrl() {
        return videoUrl
    }

    void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl
    }

    String getImgUrl() {
        return imgUrl
    }

    void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl
    }

    Teacher getTeacher() {
        return teacher
    }

    void setTeacher(Teacher teacher) {
        this.teacher = teacher
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

    Date getCreateTime() {
        return createTime
    }

    void setCreateTime(Date createTime) {
        this.createTime = createTime
    }


    Integer getVideoType() {
        return videoType
    }

    void setVideoType(Integer videoType) {
        this.videoType = videoType
    }


    @Override
    public String toString() {
        return "Video{" +
                "videoId=" + videoId +
                ", videoName='" + videoName + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", teacher=" + teacher +
                ", subject=" + subject +
                ", grade=" + grade +
                ", createTime=" + createTime +
                ", videoType=" + videoType +
                '}';
    }
}
