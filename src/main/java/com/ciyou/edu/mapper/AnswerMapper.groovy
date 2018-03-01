package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Answer
import org.apache.ibatis.annotations.Insert

/**
 * @Author C.
 * @Date 2018-03-01 15:24
 */
interface AnswerMapper {
    @Insert("insert into Answer(sid,quizId,question,questionType,answer,goodAnswer,score) values(#{student.sid},#{quiz.quizId},#{question},#{questionType},#{answer},#{goodAnswer},#{score})")
    int addAnswer(Answer answer)
}
