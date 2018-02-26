package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Judge
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.One
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

/**
 * @Author C.
 * @Date 2018-02-26 16:00
 */
interface JudgeMapper {

    @Select("select * from Judge where quizId = #{quizId}")
    List<Judge> getJudgesByQuiz(@Param("quizId")Integer quizId)

    @Insert("Insert into Judge(quizId,question,answer) values(#{quiz.quizId},#{question},#{answer})")
    int addJudge(Judge judge)

    @Select("select * from Judge where judgeId = #{judgeId}")
    @Results([
            //查询关联对象
            @Result(property = "quiz",
                    column = "quizId",
                    one = @One(select = "com.ciyou.edu.mapper.QuizMapper.getQuizById"))
    ])
    Judge getJudge(@Param("judgeId")Integer judgeId)

    @Update("Update Judge set question = #{question},answer = #{answer} where judgeId = #{judgeId}")
    int updateJudge(Judge judge)
}
