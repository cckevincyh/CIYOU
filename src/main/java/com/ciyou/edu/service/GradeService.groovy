package com.ciyou.edu.service

import com.ciyou.edu.entity.Grade

/**
 * @Author C.
 * @Date 2018-02-09 20:11
 */
interface GradeService {
    List<Grade> findAllGrade()

    int addGrade(Grade grade)

    int updateGrade(Grade grade)

    Grade getGrade(Integer gradeId)

    int deleteGrade(Integer gradeId)

}
