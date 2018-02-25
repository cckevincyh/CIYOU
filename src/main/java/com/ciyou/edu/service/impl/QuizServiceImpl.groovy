package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Quiz
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

    @Override
    Page<Quiz> findByPage(int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return quizMapper?.findAllQuiz()
    }

    @Override
    int addQuiz(Quiz quiz) {
        return quizMapper?.addQuiz(quiz)
    }
}
