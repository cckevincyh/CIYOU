package com.ciyou.edu.service

import com.ciyou.edu.entity.Judge

/**
 * @Author C.
 * @Date 2018-02-26 20:45
 */
interface JudgeService {
    int addJudge(Judge judge)

    Judge getJudge(Integer judgeId)
}
