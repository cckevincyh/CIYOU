package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Role
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 * @Author C.
 * @Date 2018-02-02 22:08
 */
interface RoleMapper {

    @Select("select Role.* from Role,Admin where Admin.adminId = #{adminId} and Role.roleId = Admin.roleId")
    Role findRoleById(@Param("adminId") Integer id)
}
