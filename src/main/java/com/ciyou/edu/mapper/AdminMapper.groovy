package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Admin
import com.github.pagehelper.Page
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

/**
 * @Author C.
 * @Date 2018-02-02 16:55
 */
interface AdminMapper {

    @Insert("insert into Admin(adminName,password,name,phone) values(#{adminName},#{password},#{name},#{phone})")
    int addAdmin(Admin admin)

    @Select("select * from Admin where adminId = #{adminId} and isAvalible = 1")
    Admin findAdminById(@Param("adminId")Integer id)

    @Select("select * from Admin where adminName = #{adminName} and isAvalible = 1")
    Admin findAdminByName(@Param("adminName")String name)

    //不允许查看到超级管理员
    @Select("select * from Admin where adminId <> #{adminId} and adminId <> 1 and isAvalible = 1 order by adminId")
    Page<Admin> findAllAdmin(@Param("adminId")Integer id)

    @Update("update Admin set name = #{name} , phone = #{phone} where adminId = #{adminId}")
    int updateAdmin(Admin admin)

    @Update("update Admin set isAvalible = 0 where adminId = #{adminId}")
    int deleteAdmin(@Param("adminId")Integer id)

    //在mybatis里面写就是应该是 like  '%${name} %' 而不是 '%#{name} %'
    //否则报错：Parameter index out of range (1 > number of parameters, which is 0)类似的错误
    @Select("select * from Admin where adminId <> #{adminId} and isAvalible = 1 and ( adminId like '%\${value}%' or adminName like '%\${value}%' or  name like '%\${value}%' or phone like '%\${value}%' ) order by adminId")
    Page<Admin> queryAdmin(@Param("adminId")Integer id,@Param("value")String value)

    @Update("update Admin set password = #{password} where adminId = #{adminId}")
    int updatePassword(@Param("adminId")Integer id, @Param("password")String password)

    @Insert("insert into Admin_Permission(adminId,permissionId) values(#{adminId},#{permissionId})")
    int setAdminPermission(@Param("adminId")Integer adminId, @Param("permissionId")Integer permissionId)

    @Delete("Delete from Admin_Permission where adminId = #{adminId}")
    int deletePermissionByAdmin(@Param("adminId")Integer adminId)
}
