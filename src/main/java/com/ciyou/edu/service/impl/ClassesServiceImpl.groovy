package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Classes
import com.ciyou.edu.mapper.ClassesMapper
import com.ciyou.edu.service.ClassesService
import com.github.pagehelper.Page
import com.github.pagehelper.PageHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @Author C.
 * @Date 2018-02-10 9:22
 */
@Service
class ClassesServiceImpl implements ClassesService{

    @Autowired
    private ClassesMapper classesMapper

    @Override
    Page<Classes> findByPage(int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return classesMapper?.findByPage()
    }

    @Transactional
    @Override
    int addClasses(Integer gradeId, Integer classes) {
        return classesMapper?.addClasses(gradeId,classes)
    }

    @Override
    Classes getClasses(Integer classesId) {
        return classesMapper?.getClasses(classesId)
    }

    @Transactional
    @Override
    int updateClasses(Integer classesId, Integer gradeId, Integer classes) {
        return classesMapper?.updateClasses(classesId,gradeId,classes)
    }

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
}
