package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Choice
import com.ciyou.edu.mapper.ChoiceMapper
import com.ciyou.edu.service.ChoiceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Author C.
 * @Date 2018-02-26 18:23
 */
@Service
class ChoiceServiceImpl implements ChoiceService{

    @Autowired
    private ChoiceMapper choiceMapper

    @Override
    int addChoice(Choice choice) {
        return choiceMapper?.addChoice(choice)
    }

    @Override
    Choice getChoice(Integer choiceId) {
        return choiceMapper?.getChoice(choiceId)
    }

    @Override
    int updateChoice(Choice choice) {
        return choiceMapper?.updateChoice(choice)
    }

    @Override
    int deleteChoice(Integer choiceId) {
        return choiceMapper?.deleteChoice(choiceId)
    }
}
