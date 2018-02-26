package com.ciyou.edu.service

import com.ciyou.edu.entity.Quiz
import com.github.pagehelper.Page

/**
 * @Author C.
 * @Date 2018-02-24 23:22
 */
interface QuizService {

    Page<Quiz> findByPage(int pageNo, int pageSize)

    int addQuiz(Quiz quiz)

    Quiz getQuizById(Integer quizId)
}
