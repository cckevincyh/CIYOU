package com.ciyou.edu.service

import com.ciyou.edu.entity.Choice

/**
 * @Author C.
 * @Date 2018-02-26 18:22
 */
interface ChoiceService {
    int addChoice(Choice choice)

    Choice getChoice(Integer choiceId)
}
