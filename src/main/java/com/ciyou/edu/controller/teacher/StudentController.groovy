package com.ciyou.edu.controller.teacher

import com.ciyou.edu.entity.PageInfo
import com.ciyou.edu.entity.Student
import com.ciyou.edu.entity.Teacher
import com.ciyou.edu.service.StudentService
import com.github.pagehelper.Page
import org.apache.shiro.SecurityUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class)

    @Autowired
    private StudentService studentService

    @RequestMapping("/teacher/manageStudent")
    ModelAndView findStudentByPage(Integer page){
        if(page == null){
            page = 1
        }
        ModelAndView mv = new ModelAndView("teacher/manageStudent")
        logger.info("findStudentByPage : 查询第${page}页")
        //获得当前老师
        Teacher teacher = (Teacher)SecurityUtils?.getSubject()?.getPrincipal()
        //不赋值pageSize，默认为10
        Page<Student> students = studentService?.findByTeacherAndPage(teacher?.getTid(),page)
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Student> pageInfo = new PageInfo<Student>(students)
        logger.info("查询结果：" + pageInfo )
        pageInfo?.setUrl("/teacher/manageStudent?")
        mv?.addObject("pageInfo",pageInfo)
        return mv
    }



    @RequestMapping(value="/teacher/queryStudent")
    ModelAndView queryStudent(String searchContent,Integer page){
        if(page == null){
            page = 1
        }
        if(!searchContent || searchContent?.trim() == ""){
            ModelAndView mv = new ModelAndView("redirect:manageStudent")
            return mv
        }else{
            ModelAndView mv = new ModelAndView("/teacher/manageStudent")
            logger.info("queryStudent : 查询第${page}页，携带查询参数=${searchContent}")
            //获得当前老师
            Teacher teacher = (Teacher)SecurityUtils?.getSubject()?.getPrincipal()
            //不赋值pageSize，默认为10
            Page<Student> students = studentService?.queryStudentByTeacherAndPage(teacher?.getTid(),searchContent?.trim(),page)
            // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
            PageInfo<Student> pageInfo = new PageInfo<Student>(students)
            pageInfo?.setUrl("/teacher/queryStudent?searchContent=${searchContent}&")
            mv?.addObject("pageInfo",pageInfo)
            return mv
        }

    }


}
