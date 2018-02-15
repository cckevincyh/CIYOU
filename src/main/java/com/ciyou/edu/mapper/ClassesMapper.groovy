package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Classes
import com.github.pagehelper.Page
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.One
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

/**
 * @Author C.
 * @Date 2018-02-10 9:24
 */
interface ClassesMapper {

    @Select("select * from Classes order by classesId")
    @Results([
        //查询关联对象
        @Result(property = "grade",
                column = "gradeId",
                one = @One(select = "com.ciyou.edu.mapper.GradeMapper.getGrade"))])
    Page<Classes> findByPage()

    @Insert("insert into Classes( gradeId, classes ) values ( #{gradeId}, #{classes} )")
    int addClasses(@Param("gradeId")Integer gradeId, @Param("classes")Integer classes)

    @Select("select * from Classes where classesId = #{classesId}")
    @Results([
            //查询关联对象
            @Result(property = "grade",
                    column = "gradeId",
                    one = @One(select = "com.ciyou.edu.mapper.GradeMapper.getGrade"))])
    Classes getClasses(@Param("classesId")Integer classesId)

    @Update("update Classes set gradeId = #{gradeId}, classes = #{classes} where classesId = #{classesId}")
    int updateClasses(@Param("classesId")Integer classesId, @Param("gradeId")Integer gradeId,@Param("classes")Integer classes)

    @Delete("delete from Classes where classesId = #{classesId}")
    int deleteClasses(@Param("classesId")Integer classesId)

    @Select("select Classes.* from Classes, Grade where Classes.gradeId = Grade.gradeId and ( Classes.classesId like '%\${value}%' or Classes.classes like '%\${value}%' or  Grade.gradeName like '%\${value}%' ) order by Classes.classesId")
    @Results([
            //查询关联对象
            @Result(property = "grade",
                    column = "gradeId",
                    one = @One(select = "com.ciyou.edu.mapper.GradeMapper.getGrade"))])
    Page<Classes> queryClassesByPage(@Param("value")String value)

    @Select("select * from Classes where gradeId = #{gradeId}")
    List<Classes> getClassesByGrade(@Param("gradeId")Integer gradeId)
}
