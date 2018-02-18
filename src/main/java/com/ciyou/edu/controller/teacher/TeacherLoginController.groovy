package com.ciyou.edu.controller.teacher

import com.ciyou.edu.config.shiro.common.LoginType
import com.ciyou.edu.config.shiro.common.UserToken
import com.ciyou.edu.entity.Teacher
import com.ciyou.edu.utils.JSONUtil
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.subject.Subject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

/**
 * @Author C.
 * @Date 2018-02-18 13:49
 */
@Controller
class TeacherLoginController {
    private static final Logger logger = LoggerFactory.getLogger(TeacherLoginController.class)
    private static final String TEACHER_LOGIN_TYPE = LoginType.TEACHER.toString()

    @RequestMapping(value="/teacherLogin",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String login(Teacher teacher){
        logger.info("登录Teacher: " + teacher)
        //后台校验提交的用户名和密码
        if(!teacher?.getTeacherId() || teacher?.getTeacherId()?.trim() == ""){
            return JSONUtil.returnFailReuslt("教师号不能为空")
        }else if(!teacher?.getPassword() || teacher?.getPassword()?.trim() == ""){
            return JSONUtil.returnFailReuslt("密码不能为空")
        }else if(teacher?.getPassword()?.length() < 3 || teacher?.getPassword()?.length() >15){
            return JSONUtil.returnFailReuslt("密码长度必须在3~15之间")
        }

        Subject subject = SecurityUtils.getSubject()

        UserToken userToken = new UserToken(teacher?.getTeacherId(), teacher?.getPassword(), TEACHER_LOGIN_TYPE)
        userToken.setRememberMe(false)
        try {
            //认证
            subject?.login(userToken)
            SecurityUtils.getSubject()?.getSession()?.setAttribute("teacher",(Teacher)subject?.getPrincipal())
            return JSONUtil.returnSuccessResult("登录成功")
        } catch (AuthenticationException e) {
            //认证失败就会抛出AuthenticationException这个异常，就对异常进行相应的操作，这里的处理是抛出一个自定义异常ResultException
            //到时候我们抛出自定义异常ResultException，用户名或者密码错误
            logger.info("认证错误：" + e.getMessage())
            return JSONUtil.returnFailReuslt("账号或者密码错误")
        }
    }
}
