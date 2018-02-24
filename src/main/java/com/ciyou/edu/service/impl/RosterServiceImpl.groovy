package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Roster
import com.ciyou.edu.entity.Teacher
import com.ciyou.edu.mapper.RosterMapper
import com.ciyou.edu.service.RosterService
import com.github.pagehelper.Page
import com.github.pagehelper.PageHelper
import org.apache.shiro.SecurityUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Author C.
 * @Date 2018-02-23 21:37
 */
@Service
class RosterServiceImpl implements RosterService{
    @Autowired
    private RosterMapper rosterMapper

    @Override
    Page<Roster> findByPage(int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        //获取当前Teacher
        Teacher teacher = (Teacher)SecurityUtils.getSubject()?.getPrincipal()
        return rosterMapper?.findByTeacher(teacher)
    }

    @Override
    Roster getRosterById(Integer rid) {
        return rosterMapper?.getRosterById(rid)
    }

    @Override
    int addRoster(Integer classesId) {
        //获得当前教师
        Teacher teacher = (Teacher)SecurityUtils.getSubject()?.getPrincipal()
        //获得教授的课程
        Integer subjectId = teacher?.getSubject()?.getSubjectId()
        Integer tid = teacher?.getTid()
        if(classesId != null && subjectId != null && tid != null){
            return rosterMapper?.addRoster(tid,classesId,subjectId)
        }else{
            return 0
        }
    }
}
