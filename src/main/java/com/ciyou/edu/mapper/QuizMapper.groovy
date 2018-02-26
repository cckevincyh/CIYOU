package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Quiz
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
 * @Date 2018-02-24 23:23
 */
interface QuizMapper {
    @Select("select * from Quiz")
    @Results([
            //查询关联对象
            @Result(property = "grade",
                    column = "gradeId",
                    one = @One(select = "com.ciyou.edu.mapper.GradeMapper.getGrade")),
            @Result(property = "teacher",
                    column = "tid",
                    one = @One(select = "com.ciyou.edu.mapper.TeacherMapper.getTeacherByTid")),
            @Result(property = "subject",
                    column = "subjectId",
                    one = @One(select = "com.ciyou.edu.mapper.SubjectMapper.getSubject"))
    ])
    Page<Quiz> findAllQuiz()

    @Insert("Insert into Quiz(quizName,subjectId,gradeId,quizTime,choiceScore,judgeScore,tid) values(#{quizName},#{subject.subjectId},#{grade.gradeId},#{quizTime},#{choiceScore},#{judgeScore},#{teacher.tid})")
    int addQuiz(Quiz quiz)

    @Select("select * from Quiz where quizId = #{quizId}")
    @Results([
            //查询关联对象
            @Result(property = "grade",
                    column = "gradeId",
                    one = @One(select = "com.ciyou.edu.mapper.GradeMapper.getGrade")),
            @Result(property = "teacher",
                    column = "tid",
                    one = @One(select = "com.ciyou.edu.mapper.TeacherMapper.getTeacherByTid")),
            @Result(property = "subject",
                    column = "subjectId",
                    one = @One(select = "com.ciyou.edu.mapper.SubjectMapper.getSubject"))
    ])
    Quiz getQuizById(@Param("quizId")Integer quizId)

    @Update("Update Quiz set quizName = #{quizName},subjectId = #{subject.subjectId},gradeId = #{grade.gradeId},quizTime = #{quizTime},choiceScore = #{choiceScore},judgeScore = #{judgeScore} where quizId = #{quizId}")
    int updateQuiz(Quiz quiz)

    @Delete("Delete from Quiz where quizId = #{quizId}")
    int deleteQuiz(@Param("quizId")Integer quizId)
}
