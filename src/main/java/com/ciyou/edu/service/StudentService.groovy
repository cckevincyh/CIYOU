package com.ciyou.edu.service

import com.ciyou.edu.entity.Student


/**
 * @Author C.
 * @Date 2018-02-03 12:16
 */
interface StudentService {
    Student findByStudentId(String studentId)
}
