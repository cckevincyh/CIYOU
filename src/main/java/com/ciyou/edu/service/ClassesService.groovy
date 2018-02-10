package com.ciyou.edu.service

import com.ciyou.edu.entity.Classes
import com.github.pagehelper.Page

/**
 * @Author C.
 * @Date 2018-02-10 9:22
 */
interface ClassesService {
    Page<Classes> findByPage(int pageNo, int pageSize)

    int addClasses(Integer gradeId, Integer classes)
}
