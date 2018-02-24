package com.ciyou.edu.service

import com.ciyou.edu.entity.Roster
import com.github.pagehelper.Page

/**
 * @Author C.
 * @Date 2018-02-23 21:37
 */
interface RosterService {
    Page<Roster> findByPage(int pageNo, int pageSize)

    Roster getRosterById(Integer rid)

    int addRoster(Integer classesId)

    int updateRoster(Integer classesId)
}
