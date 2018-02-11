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
}
