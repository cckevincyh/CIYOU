package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Permission
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 * @Author C.
 * @Date 2018-02-02 23:04
 */
interface PermissionMapper {

    @Select("select Permission.* from Admin_Permission,Permission where Admin_Permission.adminId = #{adminId} and Admin_Permission.PermissionId = Permission.PermissionId")
    List<Permission> findPermissionByAdmin(@Param("adminId")Integer id)

    @Select("select * from Permission where permissionId = #{permissionId}")
    Permission findPermissionById(@Param("permissionId")Integer id)

    @Select("select * from Permission")
    List<Permission> findAllPermission()

    @Select("select * from Permission where type = #{type} order by permissionId")
    List<Permission> findPermissionByType(@Param("type")Integer type)

    @Select("select * from Permission where parentId = #{permissionId} order by permissionId")
    List<Permission> findChildPermission(@Param("permissionId")Integer id)

    @Select("select * from Permission where permissionName = #{permissionName}")
    Permission findPermissionByName(@Param("permissionName")String name)

    @Select("select * from Permission where permissionName = #{permissionName} and permissionId <> #{permissionId}")
    Permission findOtherPermissionByName(@Param("permissionId")Integer id,@Param("permissionName")String name)

    @Insert("insert into Permission(permissionName,permission,url,type,parentId) values(#{permissionName},#{permission},#{url},#{type},#{parentId})")
    int addPermission(Permission permission)

}
