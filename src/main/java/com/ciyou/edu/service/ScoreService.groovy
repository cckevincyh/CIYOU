package com.ciyou.edu.service

import com.ciyou.edu.entity.ResultScore
import com.ciyou.edu.entity.Score
import com.github.pagehelper.Page

/**
 * @Author C.
 * @Date 2018-03-01 13:23
 */
interface ScoreService {
    Score getScore(Integer sid,Integer quizId)

    Page<Score> findMyScoreByPage(Integer sid,int pageNo, int pageSize)

    ResultScore getResultScore(Integer scoreId)
}
