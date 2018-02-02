package com.ciyou.edu.admin

import com.ciyou.edu.app.App
import com.ciyou.edu.entity.Admin
import com.ciyou.edu.entity.Permission
import com.ciyou.edu.entity.Role
import com.ciyou.edu.mapper.AdminMapper
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * @Author C.
 * @Date 2018-02-02 18:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
class AdminTest {

    @Autowired
    private AdminMapper adminMapper

    @Test
    void addAdminTest(){
        Admin admin = new Admin()
        admin.setAdminName("1111")
        admin.setPassword("1111")
        admin.setIsAvalible(1)
        Role role = new Role()
        role.setRoleId(1)
        admin.setRole(role)
        Permission p = new Permission()
        p.setPermissionId(1)
        admin.setPermission(p)
        println adminMapper.addAdmin(admin)
    }
}
