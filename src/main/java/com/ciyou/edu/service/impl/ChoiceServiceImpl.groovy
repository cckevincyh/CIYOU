package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Choice
import com.ciyou.edu.entity.Quiz
import com.ciyou.edu.mapper.ChoiceMapper
import com.ciyou.edu.mapper.QuizMapper
import com.ciyou.edu.service.ChoiceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @Author C.
 * @Date 2018-02-26 18:23
 */
@Service
class ChoiceServiceImpl implements ChoiceService{

    @Autowired
    private ChoiceMapper choiceMapper

    @Autowired
    private QuizMapper quizMapper

    @Transactional
    @Override
    int addChoice(Choice choice) {
        Quiz quiz = quizMapper?.getQuizById(choice?.getQuiz()?.getQuizId())
        quizMapper?.updateChoiceNum(quiz?.getChoiceNum() + 1,quiz?.getQuizId())
        quizMapper?.updateAllScore(quiz?.getAllScore() + quiz?.getChoiceScore(),quiz?.getQuizId())
        return choiceMapper?.addChoice(choice)
    }

    @Override
    Choice getChoice(Integer choiceId) {
        return choiceMapper?.getChoice(choiceId)
    }

    @Override
    int updateChoice(Choice choice) {
        return choiceMapper?.updateChoice(choice)
    }

    @Transactional
    @Override
    int deleteChoice(Integer choiceId) {
        Quiz quiz = quizMapper?.getQuizById(choiceMapper?.getChoice(choiceId)?.getQuiz()?.getQuizId())
        quizMapper?.updateChoiceNum(quiz?.getChoiceNum() - 1,quiz?.getQuizId())
        quizMapper?.updateAllScore(quiz?.getAllScore() - quiz?.getChoiceScore(),quiz?.getQuizId())
        return choiceMapper?.deleteChoice(choiceId)
    }
}
