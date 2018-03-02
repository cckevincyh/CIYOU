package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Score
import com.github.pagehelper.Page
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.One
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select

/**
 * @Author C.
 * @Date 2018-03-01 13:24
 */
interface ScoreMapper {
    @Select("select * from Score where quizId = #{quizId} and sid = #{sid}")
    @Results([
            //查询关联对象
            @Result(property = "student",
                    column = "sid",
                    one = @One(select = "com.ciyou.edu.mapper.StudentMapper.getStudentById")),
            @Result(property = "quiz",
                    column = "quizId",
                    one = @One(select = "com.ciyou.edu.mapper.QuizMapper.getQuizById"))
    ])
    Score getScore(@Param("sid")Integer sid, @Param("quizId")Integer quizId)

    @Insert("insert into Score(sid,quizId,choiceScore,judgeScore,allScore) values(#{student.sid},#{quiz.quizId},#{choiceScore},#{judgeScore},#{allScore})")
    int addScore(Score score)

    @Select("select * from Score where sid = #{sid}")
    @Results([
            //查询关联对象
            @Result(property = "student",
                    column = "sid",
                    one = @One(select = "com.ciyou.edu.mapper.StudentMapper.getStudentById")),
            @Result(property = "quiz",
                    column = "quizId",
                    one = @One(select = "com.ciyou.edu.mapper.QuizMapper.getQuizById"))
    ])
    Page<Score> findMyScoreByPage(@Param("sid")Integer sid)

    @Select("select * from Score where scoreId = #{scoreId}")
    @Results([
            //查询关联对象
            @Result(property = "student",
                    column = "sid",
                    one = @One(select = "com.ciyou.edu.mapper.StudentMapper.getStudentById")),
            @Result(property = "quiz",
                    column = "quizId",
                    one = @One(select = "com.ciyou.edu.mapper.QuizMapper.getQuizById"))
    ])
    Score getScoreById(@Param("scoreId")Integer scoreId)

    @Select("select * from Score,Quiz,Teacher,Grade,Subject,Student,Roster where Roster.tid = #{tid} and Roster.classesId = Student.classesId and Score.sid = Student.sid and  Quiz.tid = Teacher.tid and Quiz.gradeId = Grade.gradeId and Quiz.subjectId = Subject.subjectId and Score.quizId = Quiz.quizId and (Student.studentId like '%\${value}%' or Student.name like '%\${value}%' or Quiz.quizName like '%\${value}%' or Teacher.name like '%\${value}%' or Subject.subjectName like '%\${value}%' or Grade.gradeName like '%\${value}%' or Quiz.quizTime like '%\${value}%')")
    @Results([
            //查询关联对象
            @Result(property = "student",
                    column = "sid",
                    one = @One(select = "com.ciyou.edu.mapper.StudentMapper.getStudentById")),
            @Result(property = "quiz",
                    column = "quizId",
                    one = @One(select = "com.ciyou.edu.mapper.QuizMapper.getQuizById"))
    ])
    Page<Score> queryScoreByPage(@Param("tid")Integer tid,@Param("value")String value)

    @Select("select * from Score,Quiz,Teacher,Grade,Subject,Student where Score.sid = Student.sid and Quiz.tid = Teacher.tid and Quiz.gradeId = Grade.gradeId and Quiz.subjectId = Subject.subjectId and Score.quizId = Quiz.quizId and Student.sid = #{sid} and (Quiz.quizName like '%\${value}%' or Teacher.name like '%\${value}%' or Subject.subjectName like '%\${value}%' or Grade.gradeName like '%\${value}%' or Quiz.quizTime like '%\${value}%')")
    @Results([
            //查询关联对象
            @Result(property = "student",
                    column = "sid",
                    one = @One(select = "com.ciyou.edu.mapper.StudentMapper.getStudentById")),
            @Result(property = "quiz",
                    column = "quizId",
                    one = @One(select = "com.ciyou.edu.mapper.QuizMapper.getQuizById"))
    ])
    Page<Score> queryMyScoreByPage(@Param("sid")Integer sid, @Param("value")String value)

    @Select("select Score.* from Score,Roster,Student where Roster.tid = #{tid} and Roster.classesId = Student.classesId and Score.sid = Student.sid")
    @Results([
            //查询关联对象
            @Result(property = "student",
                    column = "sid",
                    one = @One(select = "com.ciyou.edu.mapper.StudentMapper.getStudentById")),
            @Result(property = "quiz",
                    column = "quizId",
                    one = @One(select = "com.ciyou.edu.mapper.QuizMapper.getQuizById"))
    ])
    Page<Score> findScoreByPage(@Param("tid")Integer tid)
}
