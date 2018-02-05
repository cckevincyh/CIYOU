package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Permission
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
}
