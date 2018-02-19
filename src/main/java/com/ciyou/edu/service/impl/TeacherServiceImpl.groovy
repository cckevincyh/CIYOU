package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Teacher
import com.ciyou.edu.mapper.TeacherMapper
import com.ciyou.edu.service.TeacherService
import com.github.pagehelper.Page
import com.github.pagehelper.PageHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Author C.
 * @Date 2018-02-16 23:44
 */
@Service
class TeacherServiceImpl implements TeacherService{

    @Autowired
    private TeacherMapper teacherMapper

    @Override
    Page<Teacher> findByPage(int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return teacherMapper?.findAllTeacher()
    }

    @Override
    Teacher findByTeacherId(String teacherId) {
        return teacherMapper?.findByTeacherId(teacherId)
    }

    @Override
    int addTeacher(Teacher teacher) {
        return teacherMapper?.addTeacher(teacher)
    }

    @Override
    Teacher getTeacherByTid(Integer tid) {
        return teacherMapper?.getTeacherByTid(tid)
    }

    @Override
    int updateTeacher(Teacher teacher) {
        return teacherMapper?.updateTeacher(teacher)
    }

    @Override
    int deleteTeacher(Integer tid) {
        return teacherMapper?.deleteTeacher(tid)
    }

    @Override
    Page<Teacher> queryTeacherByPage(String search, int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return teacherMapper?.queryTeacherByPage(search)
    }

    @Override
    int updateProfile(Teacher teacher) {
        return teacherMapper?.updateProfile(teacher)
    }

    @Override
    int updatePassword(Integer tid, String password) {
        return teacherMapper?.updatePassword(tid,password)
    }
}
