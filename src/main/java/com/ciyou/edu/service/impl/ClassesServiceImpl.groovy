package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Classes
import com.ciyou.edu.mapper.ClassesMapper
import com.ciyou.edu.service.ClassesService
import com.github.pagehelper.Page
import com.github.pagehelper.PageHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Author C.
 * @Date 2018-02-10 9:22
 */
@Service
class ClassesServiceImpl implements ClassesService{

    @Autowired
    private ClassesMapper classesMapper

    @Override
    Page<Classes> findByPage(int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return classesMapper?.findByPage()
    }
}
