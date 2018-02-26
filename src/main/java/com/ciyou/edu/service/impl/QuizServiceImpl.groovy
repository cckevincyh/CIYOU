package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Judge
import com.ciyou.edu.entity.Quiz
import com.ciyou.edu.mapper.ChoiceMapper
import com.ciyou.edu.mapper.JudgeMapper
import com.ciyou.edu.mapper.QuizMapper
import com.ciyou.edu.service.QuizService
import com.github.pagehelper.Page
import com.github.pagehelper.PageHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Author C.
 * @Date 2018-02-24 23:23
 */
@Service
class QuizServiceImpl implements QuizService{

    @Autowired
    private QuizMapper quizMapper

    @Autowired
    private ChoiceMapper choiceMapper

    @Autowired
    private JudgeMapper judgeMapper

    @Override
    Page<Quiz> findByPage(int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return quizMapper?.findAllQuiz()
    }

    @Override
    int addQuiz(Quiz quiz) {
        return quizMapper?.addQuiz(quiz)
    }

    @Override
    Quiz getQuizById(Integer quizId) {
        Quiz quiz = quizMapper?.getQuizById(quizId)
        quiz?.setChoices(choiceMapper?.getChoicesByQuiz(quizId))
        quiz?.setJudges(judgeMapper?.getJudgesByQuiz(quizId))
        return quiz
    }

    @Override
    int updateQuiz(Quiz quiz) {
        return quizMapper?.updateQuiz(quiz)
    }

    @Override
    int deleteQuiz(Integer quizId) {
        return quizMapper?.deleteQuiz(quizId)
    }

    @Override
    Page<Quiz> queryQuizByPage(String value, int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return quizMapper?.queryQuizByPage(value)
    }
}
