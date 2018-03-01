package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Judge
import com.ciyou.edu.entity.Quiz
import com.ciyou.edu.mapper.JudgeMapper
import com.ciyou.edu.mapper.QuizMapper
import com.ciyou.edu.service.JudgeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @Author C.
 * @Date 2018-02-26 20:45
 */
@Service
class JudgeServiceImpl implements JudgeService{

    @Autowired
    private JudgeMapper judgeMapper

    @Autowired
    private QuizMapper quizMapper

    @Transactional
    @Override
    int addJudge(Judge judge) {
        Quiz quiz = quizMapper?.getQuizById(judge?.getQuiz()?.getQuizId())
        quizMapper?.updateJudgeNum(quiz?.getJudgeNum() + 1,quiz?.getQuizId())
        quizMapper?.updateAllScore(quiz?.getAllScore() + quiz?.getJudgeScore(),quiz?.getQuizId())
        return judgeMapper?.addJudge(judge)
    }

    @Override
    Judge getJudge(Integer judgeId) {
        return judgeMapper?.getJudge(judgeId)
    }

    @Override
    int updateJudge(Judge judge) {
        return judgeMapper?.updateJudge(judge)
    }

    @Transactional
    @Override
    int deleteJudge(Integer judgeId) {
        Quiz quiz = quizMapper?.getQuizById(judgeMapper?.getJudge(judgeId)?.getQuiz()?.getQuizId())
        quizMapper?.updateJudgeNum(quiz?.getJudgeNum() - 1,quiz?.getQuizId())
        quizMapper?.updateAllScore(quiz?.getAllScore() - quiz?.getJudgeScore(),quiz?.getQuizId())
        return judgeMapper?.deleteJudge(judgeId)
    }
}
