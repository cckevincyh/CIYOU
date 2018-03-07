package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.IncorrectAnswer
import com.ciyou.edu.mapper.AnswerMapper
import com.ciyou.edu.service.AnswerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Author C.
 * @Date 2018-03-07 16:49
 */
@Service
class AnswerServiceImpl implements AnswerService{

    @Autowired
    private AnswerMapper answerMapper

    @Override
    List<IncorrectAnswer> getIncorrectAnswers(Integer quizId) {
        return answerMapper?.getIncorrectAnswers(quizId)
    }
}
