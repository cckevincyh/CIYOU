package com.ciyou.edu.service

import com.ciyou.edu.entity.IncorrectAnswer

/**
 * @Author C.
 * @Date 2018-03-07 16:48
 */
interface AnswerService {
    List<IncorrectAnswer> getIncorrectAnswers(Integer quizId)
}
