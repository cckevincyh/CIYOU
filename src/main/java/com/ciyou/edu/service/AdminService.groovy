package com.ciyou.edu.service

import com.ciyou.edu.entity.Admin

/**
 * @Author C.
 * @Date 2018-02-02 20:39
 */
interface AdminService {

    int addAdmin(Admin admin)

    Admin findAdminById(Integer adminId)

    Admin findByAdminName(String adminName)
}
