package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Grade
import com.ciyou.edu.mapper.GradeMapper
import com.ciyou.edu.service.GradeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @Author C.
 * @Date 2018-02-09 20:11
 */
@Service
class GradeServiceImpl implements GradeService{

    @Autowired
    private GradeMapper gradeMapper

    @Override
    List<Grade> findAllGrade() {
        return gradeMapper?.findAllGrade()
    }

    @Transactional
    @Override
    int addGrade(Grade grade) {
        return gradeMapper?.addGrade(grade)
    }

    @Transactional
    @Override
    int updateGrade(Grade grade) {
        return gradeMapper?.updateGrade(grade)
    }

    @Override
    Grade getGrade(Integer gradeId) {
        return gradeMapper?.getGrade(gradeId)
    }

    @Transactional
    @Override
    int deleteGrade(Integer gradeId) {
        return gradeMapper?.deleteGrade(gradeId)
    }
}
