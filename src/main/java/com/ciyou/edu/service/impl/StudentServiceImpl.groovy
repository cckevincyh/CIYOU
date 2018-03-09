package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Student
import com.ciyou.edu.mapper.StudentMapper
import com.ciyou.edu.service.StudentService
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
 * @Date 2018-02-03 12:17
 */
@CacheConfig(cacheNames = "student")
@Service
class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentMapper studentMapper

    @Cacheable
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
    @Cacheable
    @Override
    Page<Student> findByPage(int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return studentMapper?.findAllStudent()
    }

    @Transactional
    @CacheEvict(allEntries=true)
    @Override
    int addStudent(Student student) {
        return studentMapper?.addStudent(student)
    }

    @Cacheable
    @Override
    Student getStudentById(String sid) {
        return studentMapper?.getStudentById(sid)
    }

    @Transactional
    @CacheEvict(allEntries=true)
    @Override
    int deleteStudent(String sid) {
        return studentMapper?.deleteStudent(sid)
    }

    @Override
    Page<Student> queryStudentByPage(String search ,int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return studentMapper?.queryStudentByPage(search)
    }

    @Transactional
    @CacheEvict(allEntries=true)
    @Override
    int updateStudent(Student student) {
        return studentMapper?.updateStudent(student)
    }

    @Cacheable
    @Override
    Page<Student> findByTeacherAndPage(Integer tid, int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return studentMapper?.findByTeacherAndPage(tid)
    }

    @Override
    Page<Student> queryStudentByTeacherAndPage(Integer tid, String search, int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return studentMapper?.queryStudentByTeacherAndPage(tid,search)
    }

    @Transactional
    @CacheEvict(allEntries=true)
    @Override
    int updatePicImg(Integer sid, String path) {
        return studentMapper?.updatePicImg(sid,path)
    }

    @Transactional
    @CacheEvict(allEntries=true)
    @Override
    int updatePassword(Integer sid, String password) {
        return studentMapper?.updatePassword(sid,password)
    }

    @Transactional
    @CacheEvict(allEntries=true)
    @Override
    int updateProfile(Student student) {
        return studentMapper?.updateProfile(student)
    }

    @Transactional
    @CacheEvict(allEntries=true)
    @Override
    int updateStudentLockState(Integer sid,Integer quizId) {
        return studentMapper?.updateStudentLockState(sid,quizId)
    }
}
