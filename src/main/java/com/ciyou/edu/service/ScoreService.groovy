package com.ciyou.edu.service

import com.ciyou.edu.entity.Score

/**
 * @Author C.
 * @Date 2018-03-01 13:23
 */
interface ScoreService {
    Score getScore(Integer sid,Integer quizId)
}
