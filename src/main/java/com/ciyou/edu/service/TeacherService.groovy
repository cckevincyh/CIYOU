package com.ciyou.edu.service

import com.ciyou.edu.entity.Teacher
import com.github.pagehelper.Page

/**
 * @Author C.
 * @Date 2018-02-16 23:44
 */
interface TeacherService {

    Page<Teacher> findByPage(int pageNo, int pageSize)

    Teacher findByTeacherId(String teacherId)

    int addTeacher(Teacher teacher)

    Teacher getTeacherByTid(Integer tid)

    int updateTeacher(Teacher teacher)

    int deleteTeacher(Integer tid)

    Page<Teacher> queryTeacherByPage(String search,int pageNo, int pageSize)
}
