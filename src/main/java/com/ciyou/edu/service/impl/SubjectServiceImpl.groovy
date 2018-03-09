package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Subject
import com.ciyou.edu.mapper.SubjectMapper
import com.ciyou.edu.service.SubjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @Author C.
 * @Date 2018-02-09 22:55
 */
@CacheConfig(cacheNames = "subject")
@Service
class SubjectServiceImpl implements SubjectService{

    @Autowired
    private SubjectMapper subjectMapper

    @Cacheable
    @Override
    List<Subject> findAllSubject() {
        return subjectMapper?.findAllSubject()
    }

    @CacheEvict(allEntries=true)
    @Transactional
    @Override
    int addSubject(Subject subject) {
        return subjectMapper?.addSubject(subject)
    }

    @CacheEvict(allEntries=true)
    @Transactional
    @Override
    int updateSubject(Subject subject) {
        return subjectMapper?.updateSubject(subject)
    }

    @Cacheable
    @Override
    Subject getSubject(Integer subjectId) {
        return subjectMapper?.getSubject(subjectId)
    }

    @CacheEvict(allEntries=true)
    @Transactional
    @Override
    int deleteSubject(Integer subjectId) {
        return subjectMapper?.deleteSubject(subjectId)
    }

    @Cacheable
    @Override
    List<Subject> getAllSubject() {
        return subjectMapper?.getAllSubject()
    }
}
