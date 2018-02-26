package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Choice
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.One
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

/**
 * @Author C.
 * @Date 2018-02-26 15:58
 */
interface ChoiceMapper {

    @Select("select * from Choice where quizId = #{quizId}")
    List<Choice> getChoicesByQuiz(@Param("quizId")Integer quizId)

    @Insert("Insert into Choice(quizId,question,optionA,optionB,optionC,optionD,answer) values(#{quiz.quizId},#{question},#{optionA},#{optionB},#{optionC},#{optionD},#{answer})")
    int addChoice(Choice choice)

    @Select("select * from Choice where choiceId = #{choiceId}")
    @Results([
            //查询关联对象
            @Result(property = "quiz",
                    column = "quizId",
                    one = @One(select = "com.ciyou.edu.mapper.QuizMapper.getQuizById"))
    ])
    Choice getChoice(Integer choiceId)

    @Update("Update Choice set question = #{question},optionA = #{optionA},optionB = #{optionB},optionC = #{optionC}, optionD = #{optionD},answer = #{answer} where choiceId = #{choiceId}")
    int updateChoice(Choice choice)
}
