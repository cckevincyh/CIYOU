package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Grade
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

/**
 * @Author C.
 * @Date 2018-02-09 20:11
 */
interface GradeMapper {
    @Select("select * from Grade order by gradeId")
    List<Grade> findAllGrade()

    @Insert("insert into Grade(gradeName) values(#{gradeName})")
    int addGrade(Grade grade)

    @Update("update Grade set gradeName = #{gradeName} where gradeId = #{gradeId}")
    int updateGrade(Grade grade)

    @Select("select * from Grade where gradeId = #{gradeId}")
    Grade getGrade(@Param("gradeId")Integer gradeId)

    @Delete("delete from Grade where gradeId = #{gradeId}")
    int deleteGrade(@Param("gradeId")Integer gradeId)

}
