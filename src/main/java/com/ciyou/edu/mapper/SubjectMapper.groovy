package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Subject
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

/**
 * @Author C.
 * @Date 2018-02-09 22:55
 */
interface SubjectMapper {

    @Select("select * from Subject order by subjectId")
    List<Subject> findAllSubject()

    @Insert("insert into Subject(subjectName) values(#{subjectName})")
    int addSubject(Subject subject)

    @Update("update Subject set subjectName = #{subjectName} where subjectId = #{subjectId}")
    int updateSubject(Subject subject)

    @Select("select * from Subject where subjectId = #{subjectId}")
    Subject getSubject(Integer subjectId)

    @Delete("delete from Subject where subjectId = #{subjectId}")
    int deleteSubject(Integer subjectId)
}
