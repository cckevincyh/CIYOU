package com.ciyou.edu.service

import com.ciyou.edu.entity.Student
import com.github.pagehelper.Page


/**
 * @Author C.
 * @Date 2018-02-03 12:16
 */
interface StudentService {
    Student findByStudentId(String studentId)

    /**
     * 分页查询
     * @param pageNo 页号
     * @param pageSize 每页显示记录数
     * @return
     */
    Page<Student> findByPage(int pageNo, int pageSize)

    int addStudent(Student student)

    Student getStudentById(String sid)

    int deleteStudent(String sid)

    Page<Student> queryStudentByPage(String search,int pageNo, int pageSize)

    int updateStudent(Student student)

    Page<Student> findByTeacherAndPage(Integer tid, int pageNo, int pageSize)

    Page<Student> queryStudentByTeacherAndPage(Integer tid,String search,int pageNo, int pageSize)
}
