package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Permission
import com.ciyou.edu.entity.TreeNode
import com.ciyou.edu.mapper.PermissionMapper
import com.ciyou.edu.service.PermissionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

    @Transactional
    @Override
    boolean addPermission(Permission permission) {
        //添加权限的同时，需要给超级管理员赋值权限，保证超级管理员访问
        permissionMapper?.addPermission(permission)
        //执行上面的添加，返回的主键是在permission对象中获取的，直接getPermissionId就可以得到了。
        return  permissionMapper?.setAdminPermission(1,permission?.getPermissionId())
    }

    @Override
    Permission findPermissionByPermission(String permission) {
        return permissionMapper?.findPermissionByPermission(permission)
    }

    @Override
    Permission findOtherPermission(Integer permissionId, String permission) {
        return permissionMapper?.findOtherPermission(permissionId,permission)
    }

    @Override
    int updatePermission(Permission permission) {
        return permissionMapper?.updatePermission(permission)
    }

    @Override
    int deletePermission(Integer permissionId) {
        return permissionMapper?.deletePermission(permissionId)
    }

    @Override
    List<Permission> findChildPermission(Integer permissionId) {
        return permissionMapper?.findChildPermission(permissionId)
    }

    @Override
    List<Integer> findAdminPermission(Integer adminId) {
        return permissionMapper?.findAdminPermission(adminId)
    }

    @Override
    List<Permission> findPermissionByAdmin(Integer adminId) {
        return permissionMapper?.findPermissionByAdmin(adminId)
    }
}
