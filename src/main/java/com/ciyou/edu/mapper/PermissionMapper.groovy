package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Permission
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 * @Author C.
 * @Date 2018-02-02 23:04
 */
interface PermissionMapper {

    @Select("select Permission.* from Admin,Permission where Admin.adminId = #{adminId} and Admin.adminId = Permission.adminId")
    List<Permission> findPermissionByAdmin(@Param("adminId")Integer id)
}
