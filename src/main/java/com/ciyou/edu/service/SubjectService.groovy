package com.ciyou.edu.service

import com.ciyou.edu.entity.Subject

/**
 * @Author C.
 * @Date 2018-02-09 22:54
 */
interface SubjectService {
    List<Subject> findAllSubject()

    int addSubject(Subject subject)

    int updateSubject(Subject subject)

    Subject getSubject(Integer subjectId)

    int deleteSubject(Integer subjectId)
}
