package com.ciyou.edu.permission

import com.ciyou.edu.app.App
import com.ciyou.edu.service.PermissionService
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
class PermissionTest {

    @Autowired
    private PermissionService permissionService

    @Test
    void getPermissionTest(){
        println permissionService?.getPermissionTree()
    }
}
