package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Teacher
import com.ciyou.edu.mapper.TeacherMapper
import com.ciyou.edu.service.TeacherService
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
 * @Date 2018-02-16 23:44
 */
@CacheConfig(cacheNames = "teacher")
@Service
class TeacherServiceImpl implements TeacherService{

    @Autowired
    private TeacherMapper teacherMapper

    @Cacheable
    @Override
    Page<Teacher> findByPage(int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return teacherMapper?.findAllTeacher()
    }

    @Cacheable
    @Override
    Teacher findByTeacherId(String teacherId) {
        return teacherMapper?.findByTeacherId(teacherId)
    }


    @Transactional
    @CacheEvict(allEntries=true)
    @Override
    int addTeacher(Teacher teacher) {
        return teacherMapper?.addTeacher(teacher)
    }

    @Cacheable
    @Override
    Teacher getTeacherByTid(Integer tid) {
        return teacherMapper?.getTeacherByTid(tid)
    }

    @Transactional
    @CacheEvict(allEntries=true)
    @Override
    int updateTeacher(Teacher teacher) {
        return teacherMapper?.updateTeacher(teacher)
    }

    @Transactional
    @CacheEvict(allEntries=true)
    @Override
    int deleteTeacher(Integer tid) {
        return teacherMapper?.deleteTeacher(tid)
    }

    @Override
    Page<Teacher> queryTeacherByPage(String search, int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return teacherMapper?.queryTeacherByPage(search)
    }

    @Transactional
    @CacheEvict(allEntries=true)
    @Override
    int updateProfile(Teacher teacher) {
        return teacherMapper?.updateProfile(teacher)
    }

    @Transactional
    @CacheEvict(allEntries=true)
    @Override
    int updatePassword(Integer tid, String password) {
        return teacherMapper?.updatePassword(tid,password)
    }

    @Transactional
    @CacheEvict(allEntries=true)
    @Override
    int updatePicImg(Integer tid,String path) {
        return teacherMapper?.updatePicImg(tid,path)
    }
}
