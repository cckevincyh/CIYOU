package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Roster
import com.ciyou.edu.entity.Teacher
import com.github.pagehelper.Page
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.One
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select

/**
 * @Author C.
 * @Date 2018-02-23 21:38
 */
interface RosterMapper {

    @Select("select * from Roster where tid = #{tid} ")
    @Results([
            //查询关联对象
            @Result(property = "classes",
                    column = "classesId",
                    one = @One(select = "com.ciyou.edu.mapper.ClassesMapper.getClasses")),
            @Result(property = "teacher",
                    column = "tid",
                    one = @One(select = "com.ciyou.edu.mapper.TeacherMapper.getTeacherByTid")),
            @Result(property = "subject",
                    column = "subjectId",
                    one = @One(select = "com.ciyou.edu.mapper.SubjectMapper.getSubject"))
    ])
    Page<Roster> findByTeacher(Teacher teacher)

    @Select("select * from Roster where rid = #{rid} ")
    @Results([
            //查询关联对象
            @Result(property = "classes",
                    column = "classesId",
                    one = @One(select = "com.ciyou.edu.mapper.ClassesMapper.getClasses")),
            @Result(property = "teacher",
                    column = "tid",
                    one = @One(select = "com.ciyou.edu.mapper.TeacherMapper.getTeacherByTid")),
            @Result(property = "subject",
                    column = "subjectId",
                    one = @One(select = "com.ciyou.edu.mapper.SubjectMapper.getSubject"))
    ])
    Roster getRosterById(@Param("rid")Integer rid)

    @Insert("insert into Roster(tid,classesId,subjectId) values(#{tid},#{classesId},#{subjectId})")
    int addRoster(@Param("tid")Integer tid,@Param("classesId")Integer classesId,@Param("subjectId")Integer subjectId)
}
