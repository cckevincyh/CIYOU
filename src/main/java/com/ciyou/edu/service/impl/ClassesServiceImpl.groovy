package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Classes
import com.ciyou.edu.mapper.ClassesMapper
import com.ciyou.edu.service.ClassesService
import com.github.pagehelper.Page
import com.github.pagehelper.PageHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @Author C.
 * @Date 2018-02-10 9:22
 */
@Service
@CacheConfig(cacheNames = "classes")
class ClassesServiceImpl implements ClassesService{

    @Autowired
    private ClassesMapper classesMapper

    @Cacheable
    @Override
    Page<Classes> findByPage(int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return classesMapper?.findByPage()
    }

    @CacheEvict(allEntries=true)
    @Transactional
    @Override
    int addClasses(Integer gradeId, Integer classes) {
        return classesMapper?.addClasses(gradeId,classes)
    }

    @Cacheable
    @Override
    Classes getClasses(Integer classesId) {
        return classesMapper?.getClasses(classesId)
    }

    @CacheEvict(allEntries=true)
    @Transactional
    @Override
    int updateClasses(Integer classesId, Integer gradeId, Integer classes) {
        return classesMapper?.updateClasses(classesId,gradeId,classes)
    }

    @CacheEvict(allEntries=true)
    @Transactional
    @Override
    int deleteClasses(Integer classesId) {
        return classesMapper?.deleteClasses(classesId)
    }

    @Override
    Page<Classes> queryClassesByPage(String value, int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return classesMapper?.queryClassesByPage(value)
    }

    @Cacheable
    @Override
    List<Classes> getClassesByGrade(Integer gradeId) {
        return classesMapper?.getClassesByGrade(gradeId)
    }
}
