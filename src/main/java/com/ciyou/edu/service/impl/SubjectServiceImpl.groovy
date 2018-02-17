package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Subject
import com.ciyou.edu.mapper.SubjectMapper
import com.ciyou.edu.service.SubjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @Author C.
 * @Date 2018-02-09 22:55
 */
@Service
class SubjectServiceImpl implements SubjectService{

    @Autowired
    private SubjectMapper subjectMapper

    @Override
    List<Subject> findAllSubject() {
        return subjectMapper?.findAllSubject()
    }

    @Transactional
    @Override
    int addSubject(Subject subject) {
        return subjectMapper?.addSubject(subject)
    }

    @Transactional
    @Override
    int updateSubject(Subject subject) {
        return subjectMapper?.updateSubject(subject)
    }

    @Override
    Subject getSubject(Integer subjectId) {
        return subjectMapper?.getSubject(subjectId)
    }

    @Transactional
    @Override
    int deleteSubject(Integer subjectId) {
        return subjectMapper?.deleteSubject(subjectId)
    }

    @Override
    List<Subject> getAllSubject() {
        return subjectMapper?.getAllSubject()
    }
}
