package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Admin
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 * @Author C.
 * @Date 2018-02-02 16:55
 */
interface AdminMapper {

    @Insert("insert into Admin(adminName,password,roleId,isAvalible) values(#{adminName},#{password},#{role.roleId},#{isAvalible})")
    int addAdmin(Admin admin)

    @Select("select * from Admin where adminId = #{adminId}")
    Admin findAdminById(@Param("adminId")Integer id)
}
