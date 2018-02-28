package com.ciyou.edu.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * @Author C.
 * @Date 2018-02-01 23:30
 */
@Controller
class IndexController {


    /**
     * 进入admin登录界面
     * @return
     */
    @RequestMapping("/adminLogin")
    public String adminLogin(){
        return "adminLogin"
    }


    /**
     *  登录成功进入后台首页
     * @return 后台首页
     */
    @RequestMapping("/admin/admin")
    public String admin(){
        return "/admin/admin"
    }

    /**
     * 进入权限管理
     * @return
     */
    @RequestMapping("/admin/managePermission")
    String managePermission(){
        return "/admin/managePermission"
    }

    /***
     * 学生登录
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "/login"
    }
    @RequestMapping("/teacherLogin")
    public String teacherLogin(){
        return "/teacherLogin"
    }


    @RequestMapping("/teacher/index")
    public String teacherIndex(){
        return "/teacher/index"
    }




}
