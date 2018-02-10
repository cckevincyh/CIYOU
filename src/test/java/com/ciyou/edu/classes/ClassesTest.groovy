package com.ciyou.edu.classes

import com.ciyou.edu.app.App
import com.ciyou.edu.service.ClassesService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * @Author C.
 * @Date 2018-02-10 11:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
class ClassesTest {

    @Autowired
    private ClassesService classesService

    @Test
    void findClassesTest(){
        println classesService?.findByPage(1)
    }
}
