package com.ciyou.edu.service

import com.ciyou.edu.entity.AvgScore
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

    Page<Score> queryScoreByPage(Integer tid,String value,int pageNo, int pageSize)

    Page<Score> queryMyScoreByPage(Integer sid,String value,int pageNo, int pageSize)

    Page<Score> findScoreByPage(Integer tid,int pageNo, int pageSize)

    List<AvgScore> getSubjectAvgByClasses(Integer classesId)

    List<AvgScore> getSubjectAvgByGrade(Integer gradeId)
}
