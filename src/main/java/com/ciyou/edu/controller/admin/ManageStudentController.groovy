package com.ciyou.edu.controller.admin

import com.ciyou.edu.entity.PageInfo
import com.ciyou.edu.entity.Student
import com.ciyou.edu.service.StudentService
import com.github.pagehelper.Page
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 * @Author C.
 * @Date 2018-02-14 23:37
 */
@Controller
class ManageStudentController {

    private static final Logger logger = LoggerFactory.getLogger(ManageStudentController.class)

    @Autowired
    private StudentService studentService

    @RequestMapping("/admin/manageStudent")
    ModelAndView findStudentByPage(Integer page){
        if(page == null){
            page = 1
        }
        ModelAndView mv = new ModelAndView("admin/manageStudent")
        logger.info("findStudentByPage : 查询第${page}页")
        //不赋值pageSize，默认为10
        Page<Student> students = studentService?.findByPage(page)
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Student> pageInfo = new PageInfo<Student>(students)
        logger.info("查询结果：" + pageInfo )
        pageInfo?.setUrl("/admin/manageStudent?")
        mv?.addObject("pageInfo",pageInfo)
        return mv
    }

}
