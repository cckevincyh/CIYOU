package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Teacher
import com.github.pagehelper.Page
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.One
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

/**
 * @Author C.
 * @Date 2018-02-16 23:45
 */
interface TeacherMapper {

    @Select("select * from Teacher where  isAvalible = 1 order by tid")
    @Results([
            //查询关联对象
            @Result(property = "subject",
                    column = "subjectId",
                    one = @One(select = "com.ciyou.edu.mapper.SubjectMapper.getSubject"))])
    Page<Teacher> findAllTeacher()

    @Select("select * from Teacher where  isAvalible = 1 and teacherId = #{teacherId} order by tid")
    @Results([
            //查询关联对象
            @Result(property = "subject",
                    column = "subjectId",
                    one = @One(select = "com.ciyou.edu.mapper.SubjectMapper.getSubject"))])
    Teacher findByTeacherId(@Param("teacherId")String teacherId)

    @Insert("insert into Teacher(teacherId,name,password,sex,createTime,mobile,email,picImg,subjectId) values(#{teacherId},#{name},#{password},#{sex},#{createTime},#{mobile},#{email},#{picImg},#{subject.subjectId})")
    int addTeacher(Teacher teacher)

    @Select("select * from Teacher where  isAvalible = 1 and tid = #{tid}")
    @Results([
            //查询关联对象
            @Result(property = "subject",
                    column = "subjectId",
                    one = @One(select = "com.ciyou.edu.mapper.SubjectMapper.getSubject"))])
    Teacher getTeacherByTid(Integer tid)


    @Update("update Teacher set name = #{name} , sex = #{sex} , mobile = #{mobile} , email = #{email} , picImg = #{picImg} , subjectId = #{subject.subjectId} where tid = #{tid}")
    int updateTeacher(Teacher teacher)
}
