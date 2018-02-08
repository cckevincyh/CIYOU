package com.ciyou.edu.admin

import com.ciyou.edu.app.App
import com.ciyou.edu.entity.Admin
import com.ciyou.edu.entity.PageInfo
import com.ciyou.edu.service.AdminService
import com.github.pagehelper.Page
import org.apache.shiro.crypto.hash.Md5Hash
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

import java.security.NoSuchAlgorithmException
import java.security.SecureRandom

/**
 * @Author C.
 * @Date 2018-02-02 18:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
class AdminTest {

    @Autowired
    private AdminService adminService

    @Test
    void addAdminTest(){
        Admin admin = new Admin()
        admin.setAdminName("admin")
        admin.setName("admin")
        admin.setIsAvalible(1)
        String passwordMd5= new Md5Hash("admin","admin",2).toHex()
        admin.setPassword(passwordMd5)
        println adminService.addAdmin(admin)
    }

    @Test
    void findAdminById(){
        Admin admin =  adminService.findAdminById(1)
        println admin.adminName
    }


    /**
     * 生成盐
     * @return
     */
    public static byte[] createSalt(){
        byte[] salt = new byte[16]
        try {
            SecureRandom random = SecureRandom.getInstance("3")
            random.nextBytes(salt)
            return salt
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    @Test
    void findByPage(){
        Page<Admin> admins = adminService.findByPage(5, 1)
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Admin> pageInfo = new PageInfo<Admin>(admins)
        println pageInfo
    }
}
