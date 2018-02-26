package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Judge
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 * @Author C.
 * @Date 2018-02-26 16:00
 */
interface JudgeMapper {

    @Select("select * from Judge where quizId = #{quizId}")
    List<Judge> getJudgesByQuiz(@Param("quizId")Integer quizId)

    @Insert("Insert into Judge(quizId,question,answer) values(#{quiz.quizId},#{question},#{answer})")
    int addJudge(Judge judge)
}
