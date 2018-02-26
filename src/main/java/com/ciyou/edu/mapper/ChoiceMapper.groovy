package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Choice
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 * @Author C.
 * @Date 2018-02-26 15:58
 */
interface ChoiceMapper {

    @Select("select * from Choice where quizId = #{quizId}")
    List<Choice> getChoicesByQuiz(@Param("quizId")Integer quizId)

    @Insert("Insert into Choice(quizId,question,optionA,optionB,optionC,optionD,answer) values(#{quiz.quizId},#{question},#{optionA},#{optionB},#{optionC},#{optionD},#{answer})")
    int addChoice(Choice choice)
}
