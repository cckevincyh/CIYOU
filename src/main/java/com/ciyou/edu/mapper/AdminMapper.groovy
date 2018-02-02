package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Admin
import org.apache.ibatis.annotations.Insert

/**
 * @Author C.
 * @Date 2018-02-02 16:55
 */
interface AdminMapper {

    @Insert("insert into Admin(adminName,password,permissionId,roleId,isAvalible) values(#{adminName},#{password},#{permission.permissionId},#{role.roleId},#{isAvalible})")
    int addAdmin(Admin admin)
}
