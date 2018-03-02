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
}
