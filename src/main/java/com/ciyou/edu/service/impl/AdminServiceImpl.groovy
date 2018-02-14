package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Admin

import com.ciyou.edu.mapper.AdminMapper
import com.ciyou.edu.service.AdminService
import com.github.pagehelper.Page
import com.github.pagehelper.PageHelper
import org.apache.shiro.SecurityUtils
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
//@CacheConfig(cacheNames = "admin")
class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminMapper adminMapper

    @Transactional
    @Override
    int addAdmin(Admin admin) {
        return adminMapper?.addAdmin(admin)
    }

    //@Cacheable：配置方法的缓存参数，可自定义缓存的key以及value
    //@Cacheable
    @Override
    Admin findAdminById(Integer adminId) {
        return adminMapper?.findAdminById(adminId)
    }

    @Override
    Admin findByAdminName(String adminName) {
        return adminMapper?.findAdminByName(adminName)
    }

    /**
     *  分页查询
     * @param pageNo 当前页面
     * @param pageSize 每页条数，默认为10
     * @return
     */
    @Override
    Page<Admin> findByPage(int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        //获取当前Admin
        Admin admin = (Admin)SecurityUtils.getSubject()?.getPrincipal()
        //查询非当前的所有Admin
        return adminMapper?.findAllAdmin(admin?.getAdminId())
    }

    @Transactional
    @Override
    int updateAdmin(Admin admin) {
        return adminMapper?.updateAdmin(admin)
    }

    @Transactional
    @Override
    int deleteAdmin(Integer adminId) {
        return adminMapper?.deleteAdmin(adminId)
    }

    @Override
    Page<Admin> queryAdminByPage(String value,int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        //获取当前Admin
        Admin admin = (Admin)SecurityUtils.getSubject()?.getPrincipal()
        //查询非当前的所有Admin
        return adminMapper?.queryAdmin(admin?.getAdminId(),value)
    }

    @Transactional
    @Override
    int updatePassword(Integer adminId, String password) {
        return adminMapper?.updatePassword(adminId,password)
    }

    @Transactional
    @Override
    boolean setAdminPermission(Integer adminId, String allPermission) {
        boolean b = true
        //先删除当前Admin的权限
        adminMapper?.deletePermissionByAdmin(adminId)
        //切割得到提交的权限，进行重新添加
        if(allPermission?.split(",")?.size()){
            allPermission?.split(",")?.each {permissionId ->
                if(permissionId != ""){
                    if(!adminMapper?.setAdminPermission(adminId, permissionId?.toInteger())){
                        b = false
                    }
                }
            }
        }
        return b
    }

}
