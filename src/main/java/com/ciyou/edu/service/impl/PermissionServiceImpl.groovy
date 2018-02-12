package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Permission
import com.ciyou.edu.entity.TreeNode
import com.ciyou.edu.mapper.PermissionMapper
import com.ciyou.edu.service.PermissionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Author C.
 * @Date 2018-02-04 17:02
 */
@Service
class PermissionServiceImpl implements PermissionService{

    @Autowired
    private PermissionMapper permissionMapper

    @Override
    List<Permission> findAllPermission() {
        return permissionMapper?.findAllPermission()
    }

    @Override
    List<TreeNode> getPermissionTree() {
        //得到type为1的，权限类型:菜单,就是父权限
        List<Permission> parentList = permissionMapper?.findPermissionByType(1)
        List<TreeNode> parentNodes = new ArrayList<TreeNode>()
        //循环每个父亲权限
        parentList?.each {parent ->
            List<TreeNode> childNodes = null
            //得到父亲的权限ID,根据父亲ID去找子权限
            List<Permission> childList = permissionMapper?.findChildPermission(parent?.getPermissionId())
            if(childList && childList?.size() > 0){
                childNodes = new ArrayList<TreeNode>()
            }
            //构造为一个TreeNode
            childList?.each {child ->
                //add 进入当前父亲的所有孩子List列表中
                childNodes << new TreeNode(child?.getPermissionId(),child?.getPermissionName(),null)
            }
            //add 进入所有父亲的List列表中
            parentNodes << new TreeNode(parent?.getPermissionId(),parent?.getPermissionName(),childNodes)
        }
        return parentNodes
    }

    @Override
    Permission findPermissionById(Integer permissionId) {
        return permissionMapper?.findPermissionById(permissionId)
    }

    @Override
    Permission findPermissionByName(String permissionName) {
        return permissionMapper?.findPermissionByName(permissionName)
    }

    @Override
    Permission findOtherPermissionByName(Integer permissionId, String permissionName) {
        return permissionMapper?.findOtherPermissionByName(permissionId, permissionName)
    }
}
