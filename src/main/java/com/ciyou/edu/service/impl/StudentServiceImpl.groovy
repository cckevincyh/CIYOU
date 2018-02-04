package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Student
import com.ciyou.edu.service.StudentService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * @Author C.
 * @Date 2018-02-03 12:17
 */
@Service
class StudentServiceImpl implements StudentService{

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class)
    @Override
    Student findByStudentId(String studentId) {
        return null
    }
}
