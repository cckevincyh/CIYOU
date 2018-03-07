package com.ciyou.edu.entity

/**
 * @Author C.
 * @Date 2018-03-06 19:21
 */
class AvgScore implements Serializable{
    private static final long serialVersionUID = 1L

    private String avg
    private String subjectName

    String getAvg() {
        return avg
    }

    void setAvg(String avg) {
        this.avg = avg
    }

    String getSubjectName() {
        return subjectName
    }

    void setSubjectName(String subjectName) {
        this.subjectName = subjectName
    }


    @Override
    public String toString() {
        return "AvgScore{" +
                "avg='" + avg + '\'' +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }
}
