package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Video
import com.github.pagehelper.Page
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.One
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select

/**
 * @Author C.
 * @Date 2018-02-20 18:31
 */
interface VideoMapper {

    @Select("select * from Video order by videoId")
    @Results([
            //查询关联对象
            @Result(property = "grade",
                    column = "gradeId",
                    one = @One(select = "com.ciyou.edu.mapper.GradeMapper.getGrade")),
            @Result(property = "teacher",
                    column = "teacherId",
                    one = @One(select = "com.ciyou.edu.mapper.TeacherMapper.getTeacherByTid")),
            @Result(property = "subject",
                    column = "subjectId",
                    one = @One(select = "com.ciyou.edu.mapper.SubjectMapper.getSubject"))
    ])
    Page<Video> findAllVideo()

    @Insert("insert into Video(videoName,videoUrl,imgUrl,teacherId,subjectId,gradeId,createTime) values(#{videoName},#{videoUrl},#{imgUrl},#{teacher.tid},#{subject.subjectId},#{grade.gradeId},#{createTime})")
    int addVideo(Video video)
}
