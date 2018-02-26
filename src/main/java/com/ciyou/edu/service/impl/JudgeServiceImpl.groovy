package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Judge
import com.ciyou.edu.mapper.JudgeMapper
import com.ciyou.edu.service.JudgeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Author C.
 * @Date 2018-02-26 20:45
 */
@Service
class JudgeServiceImpl implements JudgeService{

    @Autowired
    private JudgeMapper judgeMapper

    @Override
    int addJudge(Judge judge) {
        return judgeMapper?.addJudge(judge)
    }
}
