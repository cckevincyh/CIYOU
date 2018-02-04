package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Admin
import com.ciyou.edu.entity.Permission

import com.ciyou.edu.mapper.AdminMapper
import com.ciyou.edu.mapper.PermissionMapper
import com.ciyou.edu.service.AdminService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @Author C.
 * @Date 2018-02-02 20:42
 *
 *
 */
//@CacheConfig：该注解是用来开启声明的类参与缓存,如果方法内的@Cacheable注解没有添加key值，那么会自动使用cahceNames配置参数并且追加方法名
@Service
@CacheConfig(cacheNames = "admin")
class AdminServiceImpl implements AdminService{

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class)
    @Autowired
    private AdminMapper adminMapper
    @Autowired
    private PermissionMapper permissionMapper

    @Transactional
    @Override
    int addAdmin(Admin admin) {
        return adminMapper?.addAdmin(admin)
    }

    //@Cacheable：配置方法的缓存参数，可自定义缓存的key以及value
    @Cacheable
    @Override
    Admin findAdminById(Integer adminId) {
        Admin admin = adminMapper?.findAdminById(adminId)
        //获取权限
        List<Permission> permissionList =  permissionMapper?.findPermissionByAdmin(adminId)
        //获取父权限,去掉重复的父权限
        Set<Integer> parentSet = new HashSet<Integer>()
        permissionList?.each {current_Permission ->
            parentSet?.add(current_Permission?.getParentId())
        }
        //查询相关父权限，加入当前Admin的权限
        parentSet?.each {current_Parent ->
            Permission permission = permissionMapper?.findPermissionById(current_Parent)
            if(permission){
                permissionList?.add(permission)
            }
        }
        admin?.setPermissionList(permissionList)
        return admin
    }

    @Override
    Admin findByAdminName(String adminName) {
        Admin admin = adminMapper?.findAdminByName(adminName)
        //获取权限
        List<Permission> permissionList =  permissionMapper?.findPermissionByAdmin(admin?.getAdminId())
        //获取父权限,去掉重复的父权限
        Set<Integer> parentSet = new HashSet<Integer>()
        permissionList?.each {current_Permission ->
            parentSet?.add(current_Permission?.getParentId())
        }
        //查询相关父权限，加入当前Admin的权限
        parentSet?.each {current_Parent ->
            Permission permission = permissionMapper?.findPermissionById(current_Parent)
            if(permission){
                permissionList?.add(permission)
            }
        }
        admin?.setPermissionList(permissionList)
        return admin
    }



}
