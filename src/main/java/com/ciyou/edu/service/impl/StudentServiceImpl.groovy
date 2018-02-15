package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Student
import com.ciyou.edu.mapper.StudentMapper
import com.ciyou.edu.service.StudentService
import com.github.pagehelper.Page
import com.github.pagehelper.PageHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Author C.
 * @Date 2018-02-03 12:17
 */
@Service
class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentMapper studentMapper

    @Override
    Student findByStudentId(String studentId) {
        return studentMapper?.findByStudentId(studentId)
    }

    /**
     *  分页查询
     * @param pageNo 当前页面
     * @param pageSize 每页条数，默认为10
     * @return
     */
    @Override
    Page<Student> findByPage(int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return studentMapper?.findAllStudent()
    }

    @Override
    int addStudent(Student student) {
        return studentMapper?.addStudent(student)
    }
}
