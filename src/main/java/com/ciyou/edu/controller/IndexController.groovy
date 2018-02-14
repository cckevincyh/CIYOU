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
     * 进入首页
     * @return
     */
    @RequestMapping("/index")
    public String hello(){
        return "index"
    }

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


}
