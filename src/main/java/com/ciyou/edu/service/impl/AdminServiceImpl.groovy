package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Admin
import com.ciyou.edu.mapper.AdminMapper
import com.ciyou.edu.service.AdminService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @Author C.
 * @Date 2018-02-02 20:42
 */
@Service
@Transactional
class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminMapper adminMapper

    @Override
    int addAdmin(Admin admin) {
        return adminMapper.addAdmin(admin)
    }
}
