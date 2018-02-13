package com.ciyou.edu.service

import com.ciyou.edu.entity.Permission
import com.ciyou.edu.entity.TreeNode

/**
 * @Author C.
 * @Date 2018-02-04 17:01
 */
interface PermissionService {
    List<Permission> findAllPermission()

    List<TreeNode> getPermissionTree()

    Permission findPermissionById(Integer permissionId)

    Permission findPermissionByName(String permissionName)

    Permission findOtherPermissionByName(Integer permissionId, String permissionName)

    int addPermission(Permission permission)

    Permission findPermissionByPermission(String permission)

    Permission findOtherPermission(Integer permissionId, String permission)

    int updatePermission(Permission permission)

    int deletePermission(Integer permissionId)

    List<Permission> findChildPermission(Integer permissionId)

    List<Integer> findAdminPermission(Integer adminId)
}
