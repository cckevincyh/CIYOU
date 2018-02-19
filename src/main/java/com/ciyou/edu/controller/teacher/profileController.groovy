package com.ciyou.edu.controller.teacher

import com.ciyou.edu.entity.Subject
import com.ciyou.edu.entity.Teacher
import com.ciyou.edu.service.SubjectService
import com.ciyou.edu.service.TeacherService
import com.ciyou.edu.utils.JSONUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

import java.util.regex.Pattern

/**
 * @Author C.
 * @Date 2018-02-18 14:52
 */
@Controller
class profileController {

    private static final Logger logger = LoggerFactory.getLogger(profileController.class)
    @Autowired
    private SubjectService subjectService

    @Autowired
    private TeacherService teacherService

    @RequestMapping("/teacher/profile")
    String profile(){
        return "/teacher/profile"
    }

    @RequestMapping(value="/teacher/getAllSubject",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String getAllSubject(){
        List<Subject> subjectList = subjectService?.getAllSubject()
        return JSONUtil.returnEntityReuslt(subjectList)
    }

    @RequestMapping(value="/teacher/getTeacherByTid", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String getTeacherByTid(Integer tid){
        return JSONUtil.returnEntityReuslt(teacherService?.getTeacherByTid(tid))
    }


    @RequestMapping(value="/teacher/updateProfile", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String updateProfile(Teacher teacher, Integer subjectId){
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


}
