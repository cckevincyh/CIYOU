package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Answer
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 * @Author C.
 * @Date 2018-03-01 15:24
 */
interface AnswerMapper {
    @Insert("insert into Answer(sid,quizId,question,questionType,answer,goodAnswer,score) values(#{student.sid},#{quiz.quizId},#{question},#{questionType},#{answer},#{goodAnswer},#{score})")
    int addAnswer(Answer answer)

    @Select("select * from Answer where quizId = #{quizId} and sid = #{sid} and question = #{question} and questionType = 1")
    Answer getChoiceAnswer(@Param("sid")Integer sid, @Param("quizId")Integer quizId, @Param("question")Integer question)

    @Select("select * from Answer where quizId = #{quizId} and sid = #{sid} and question = #{question} and questionType = 2")
    Answer getJudgeAnswer(@Param("sid")Integer sid, @Param("quizId")Integer quizId, @Param("question")Integer question)
}
