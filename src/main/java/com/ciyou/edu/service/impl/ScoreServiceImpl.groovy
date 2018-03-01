package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Score
import com.ciyou.edu.mapper.ScoreMapper
import com.ciyou.edu.service.ScoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Author C.
 * @Date 2018-03-01 13:23
 */
@Service
class ScoreServiceImpl implements ScoreService{

    @Autowired
    private ScoreMapper scoreMapper

    @Override
    Score getScore(Integer sid,Integer quizId) {
        return scoreMapper?.getScore(sid,quizId)
    }
}
