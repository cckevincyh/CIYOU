package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Student
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
 * @Date 2018-02-15 9:59
 */
interface StudentMapper {

    @Select("select * from Student where  isAvalible = 1 order by sid")
    @Results([
            //查询关联对象
            @Result(property = "classes",
                    column = "classesId",
                    one = @One(select = "com.ciyou.edu.mapper.ClassesMapper.getClasses"))])
    Page<Student> findAllStudent()

    @Select("select * from Student where  isAvalible = 1 and studentId = #{studentId}")
    @Results([
            //查询关联对象
            @Result(property = "classes",
                    column = "classesId",
                    one = @One(select = "com.ciyou.edu.mapper.ClassesMapper.getClasses"))])
    Student findByStudentId(@Param("studentId")String studentId)

    @Insert("insert into Student(studentId,name,password,sex,age,createTime,mobile,parentMobile,email,parentEmail,picImg,classesId) values(#{studentId},#{name},#{password},#{sex},#{age},#{createTime},#{mobile},#{parentMobile},#{email},#{parentEmail},#{picImg},#{classes.classesId})")
    int addStudent(Student student)

    @Select("select * from Student where  isAvalible = 1 and sid = #{sid}")
    @Results([
            //查询关联对象
            @Result(property = "classes",
                    column = "classesId",
                    one = @One(select = "com.ciyou.edu.mapper.ClassesMapper.getClasses"))])
    Student getStudentById(@Param("sid")String sid)

    @Update("update Student set isAvalible = 0 where sid = #{sid}")
    int deleteStudent(@Param("sid")String sid)

    @Select("select * from Student where (studentId like '%\${value}%' or name like '%\${value}%') and isAvalible = 1 ")
    @Results([
            //查询关联对象
            @Result(property = "classes",
                    column = "classesId",
                    one = @One(select = "com.ciyou.edu.mapper.ClassesMapper.getClasses"))])
    Page<Student> queryStudentByPage(@Param("value")String value)

    @Update("update Student set name = #{name}, sex = #{sex}, age = #{age} , mobile = #{mobile} , parentMobile = #{parentMobile} , email = #{email} , parentEmail = #{parentEmail} , classesId = #{classes.classesId} where sid = #{sid}")
    int updateStudent(Student student)

    @Select("Select Student.* from Student,Roster where Student.classesId = Roster.classesId and Roster.tid = #{tid} and Student.isAvalible = 1")
    @Results([
            //查询关联对象
            @Result(property = "classes",
                    column = "classesId",
                    one = @One(select = "com.ciyou.edu.mapper.ClassesMapper.getClasses"))])
    Page<Student> findByTeacherAndPage(@Param("tid")Integer tid)

    @Select("select Student.* from Student,Roster where (Student.studentId like '%\${value}%' or Student.name like '%\${value}%') and Student.isAvalible = 1 and Student.classesId = Roster.classesId and Roster.tid = #{tid}")
    @Results([
            //查询关联对象
            @Result(property = "classes",
                    column = "classesId",
                    one = @One(select = "com.ciyou.edu.mapper.ClassesMapper.getClasses"))])
    Page<Student> queryStudentByTeacherAndPage(@Param("tid")Integer tid, @Param("value")String value)

    @Update("update Student set picImg = #{picImg} where sid = #{sid}")
    int updatePicImg(@Param("sid")Integer sid, @Param("picImg")String path)


    @Update("update Student set password = #{password} where sid = #{sid}")
    int updatePassword(@Param("sid")Integer sid, @Param("password")String password)

    @Update("update Student set name = #{name} , sex = #{sex} , mobile = #{mobile} , email = #{email} , age = #{age}, parentMobile = #{parentMobile} , parentEmail = #{parentEmail} where sid = #{sid}")
    int updateProfile(Student student)

    @Update("update Student set lockState = #{quizId} where sid = #{sid}")
    int updateStudentLockState(@Param("sid")Integer sid,@Param("quizId")Integer quizId)
}
