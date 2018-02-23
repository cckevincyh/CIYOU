package com.ciyou.edu.controller.admin

import com.ciyou.edu.entity.PageInfo
import com.ciyou.edu.entity.Subject
import com.ciyou.edu.entity.Teacher
import com.ciyou.edu.service.TeacherService
import com.ciyou.edu.utils.JSONUtil
import com.github.pagehelper.Page
import org.apache.shiro.crypto.hash.Md5Hash
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView

import java.util.regex.Pattern

/**
 * @Author C.
 * @Date 2018-02-16 23:43
 */
@Controller
class ManageTeacherController {

    private static final Logger logger = LoggerFactory.getLogger(ManageTeacherController.class)

    @Autowired
    private TeacherService teacherService

    @RequestMapping("/admin/manageTeacher")
    ModelAndView findTeacherByPage(Integer page){
        if(page == null){
            page = 1
        }
        ModelAndView mv = new ModelAndView("admin/manageTeacher")
        logger.info("findTeacherByPage : 查询第${page}页")
        //不赋值pageSize，默认为10
        Page<Teacher> teachers = teacherService?.findByPage(page)
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(teachers)
        logger.info("查询结果：" + pageInfo )
        pageInfo?.setUrl("/admin/manageTeacher?")
        mv?.addObject("pageInfo",pageInfo)
        return mv
    }

    @RequestMapping(value="/admin/addTeacher", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String addTeacher(Teacher teacher,Integer subjectId){
        logger.info("添加Teacher信息：" + teacher + "科目ID：" + subjectId)
        //校验提交的Teacher
        if(!teacher?.getTeacherId() || teacher?.getTeacherId()?.trim() == ""){
            return JSONUtil.returnFailReuslt("教师号不能为空")
        }else if(!teacher?.getName() || teacher?.getName()?.trim() == ""){
            return JSONUtil.returnFailReuslt("教师姓名不能为空")
        }else if(!Pattern.compile("[\\u4E00-\\u9FFF]+")?.matcher(teacher?.getName())?.matches()){
            return JSONUtil.returnFailReuslt("教师姓名必须为中文")
        }else if(subjectId == null || subjectId == 0){
            return JSONUtil.returnFailReuslt("科目不能为空")
        }else if(teacher?.getMobile() && !Pattern.compile('^1[34578]\\d{9}$')?.matcher(teacher?.getMobile())?.matches()){
            return JSONUtil.returnFailReuslt("电话号码有误")
        }else if(teacher?.getEmail() && !Pattern.compile('^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+')?.matcher(teacher?.getEmail())?.matches()){
            return JSONUtil.returnFailReuslt("邮箱有误")
        }
        //查找该教师号的学生是否存在
        if(teacherService?.findByTeacherId(teacher?.getTeacherId())){
            //如果已经存在
            return JSONUtil.returnFailReuslt("该教师已存在")
        }else{
            try{
                //密码默认:123456
                String passwordMd5= new Md5Hash("123456",teacher?.getTeacherId(),2).toHex()
                teacher.setPassword(passwordMd5)
                Subject subject = new Subject()
                subject.setSubjectId(subjectId)
                teacher?.setSubject(subject)
                teacher?.setCreateTime(new Date(System.currentTimeMillis()))
                //如果是男生
                if(teacher?.getSex() == 1){
                    teacher?.setPicImg("/static/img/t-boy.png")
                }else{
                    teacher?.setPicImg("/static/img/t-girl.png")
                }
                if(teacherService?.addTeacher(teacher)){
                    return JSONUtil.returnSuccessResult("添加成功")
                }else{
                    return JSONUtil.returnFailReuslt("添加失败")
                }
            }catch (Exception e){
                logger.info("添加Teacher错误：" + e.getMessage())
                return JSONUtil.returnFailReuslt("添加失败，请重试")
            }
        }

    }


    @RequestMapping(value="/admin/getTeacherByTid", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String getTeacherByTid(Integer tid){
        return JSONUtil.returnEntityReuslt(teacherService?.getTeacherByTid(tid))
    }

    @RequestMapping(value="/admin/updateTeacher", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String updateTeacher(Teacher teacher, Integer subjectId){
        logger.info("修改Teacher信息：" + teacher + "科目ID：" + subjectId)
        //校验提交的Teacher
        if(!teacher?.getName() || teacher?.getName()?.trim() == ""){
            return JSONUtil.returnFailReuslt("教师姓名不能为空")
        }else if(!Pattern.compile("[\\u4E00-\\u9FFF]+")?.matcher(teacher?.getName())?.matches()){
            return JSONUtil.returnFailReuslt("教师姓名必须为中文")
        }else if(subjectId == null || subjectId == 0){
            return JSONUtil.returnFailReuslt("科目不能为空")
        }else if(teacher?.getMobile() && !Pattern.compile('^1[34578]\\d{9}$')?.matcher(teacher?.getMobile())?.matches()){
            return JSONUtil.returnFailReuslt("电话号码有误")
        }else if(teacher?.getEmail() && !Pattern.compile('^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+')?.matcher(teacher?.getEmail())?.matches()){
            return JSONUtil.returnFailReuslt("邮箱有误")
        }
        Subject subject = new Subject()
        subject.setSubjectId(subjectId)
        teacher.setSubject(subject)

        try{
            if(teacherService?.updateTeacher(teacher)){
                return JSONUtil.returnSuccessResult("修改成功")
            }else{
                return JSONUtil.returnFailReuslt("修改失败")
            }
        }catch (Exception e){
            logger.info("修改Teacher错误：" + e.getMessage())
            return JSONUtil.returnFailReuslt("修改失败，请重试")
        }
    }


    @RequestMapping(value="/admin/deleteTeacher", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String deleteTeacher(Integer tid){
        try{
            if(teacherService?.deleteTeacher(tid)){
                return JSONUtil.returnSuccessResult("删除成功")
            }else{
                return JSONUtil.returnFailReuslt("删除失败")
            }
        }catch (Exception e){
            logger.info("删除Teacher错误：" + e.getMessage())
            return JSONUtil.returnFailReuslt("删除失败，请重试")
        }
    }


    @RequestMapping(value="/admin/queryTeacher")
    ModelAndView queryTeacher(String searchContent,Integer page){
        if(page == null){
            page = 1
        }
        if(!searchContent || searchContent?.trim() == ""){
            ModelAndView mv = new ModelAndView("redirect:manageTeacher")
            return mv
        }else{
            ModelAndView mv = new ModelAndView("/admin/manageTeacher")
            logger.info("queryTeacher : 查询第${page}页，携带查询参数=${searchContent}")
            //不赋值pageSize，默认为10
            Page<Teacher> teachers = teacherService?.queryTeacherByPage(searchContent?.trim(),page)
            // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
            PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(teachers)
            pageInfo?.setUrl("/admin/queryTeacher?searchContent=${searchContent}&")
            mv?.addObject("pageInfo",pageInfo)
            return mv
        }

    }
}
