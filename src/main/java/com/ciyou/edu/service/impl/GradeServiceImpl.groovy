package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Grade
import com.ciyou.edu.mapper.GradeMapper
import com.ciyou.edu.service.GradeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @Author C.
 * @Date 2018-02-09 20:11
 */
@CacheConfig(cacheNames = "grade")
@Service
class GradeServiceImpl implements GradeService{

    @Autowired
    private GradeMapper gradeMapper

    @Cacheable
    @Override
    List<Grade> findAllGrade() {
        return gradeMapper?.findAllGrade()
    }

    @CacheEvict(allEntries=true)
    @Transactional
    @Override
    int addGrade(Grade grade) {
        return gradeMapper?.addGrade(grade)
    }

    @CacheEvict(allEntries=true)
    @Transactional
    @Override
    int updateGrade(Grade grade) {
        return gradeMapper?.updateGrade(grade)
    }

    @Cacheable
    @Override
    Grade getGrade(Integer gradeId) {
        return gradeMapper?.getGrade(gradeId)
    }

    @CacheEvict(allEntries=true)
    @Transactional
    @Override
    int deleteGrade(Integer gradeId) {
        return gradeMapper?.deleteGrade(gradeId)
    }

}
